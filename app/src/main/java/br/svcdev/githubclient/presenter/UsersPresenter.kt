package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.Logger
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.GithubUserRepo
import br.svcdev.githubclient.view.interfaces.IUserItemView
import br.svcdev.githubclient.view.interfaces.IUsersView
import br.svcdev.githubclient.view.ui.Screens
import io.reactivex.rxjava3.core.Observable
import moxy.MvpPresenter

class UsersPresenter : MvpPresenter<IUsersView>() {

    private val usersRepo = GithubUserRepo()
    private val router = GithubClientApp.instance?.getRouter()
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
    }

    private fun loadData() {

        usersRepo.getUsers().flatMap { users -> Observable.fromIterable(users) }
                .subscribe(
                        { user ->
                            apply {
                                usersListPresenter.users.add(user)
                                logger.logi(TAG, user.login)
                            }
                        },
                        { t -> logger.logi(TAG, "Error! ${t.message}") },
                        { logger.logi(TAG, "onComplete") })

        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router?.exit()
        return false
    }

    inner class UsersListPresenter : IUsersListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = {
            if (VERBOSE) {
                logger.logi(TAG, "onItemClick " + it.pos)
            }
            router?.navigateTo(Screens.UserScreen(users[it.pos]))
        }

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }
}