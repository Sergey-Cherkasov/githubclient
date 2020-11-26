package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.common.Logger
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.repository.IGithubUsersRepo
import br.svcdev.githubclient.view.interfaces.IUserItemView
import br.svcdev.githubclient.view.interfaces.IUsersView
import br.svcdev.githubclient.view.ui.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(private val mainThreadScheduler: Scheduler,
                     private val usersRepo: IGithubUsersRepo,
                     val router: Router) : MvpPresenter<IUsersView>() {

    private val logger = Logger()

    val usersListPresenter = UsersListPresenter()

    companion object {
        private val TAG = UserPresenter::class.qualifiedName
        private val VERBOSE = true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
                .observeOn(mainThreadScheduler)
                .subscribe({ repos ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return false
    }

    inner class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login.let { view.setLogin(it) }
            user.avatarUrl.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size

    }
}