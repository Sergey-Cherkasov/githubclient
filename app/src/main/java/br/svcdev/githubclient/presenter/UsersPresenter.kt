package br.svcdev.githubclient.presenter

import android.util.Log
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.GithubUserRepo
import br.svcdev.githubclient.view.interfaces.IUserItemView
import br.svcdev.githubclient.view.interfaces.IUsersView
import br.svcdev.githubclient.view.ui.Screens
import moxy.MvpPresenter

class UsersPresenter : MvpPresenter<IUsersView>() {

    private val tag = UsersPresenter::class.simpleName
    private val verbose = true

    private val usersRepo = GithubUserRepo()
    private val router = GithubClientApp.instance?.getRouter()
    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router?.exit()
        return false
    }

    inner class UsersListPresenter : IUsersListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = {
            if (verbose) {
                Log.v(tag, "onItemClick " + it.pos)
            }
            router?.navigateTo(Screens.UserScreen(users[it.pos].login))
        }

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }
}