package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.view.interfaces.IUserView
import moxy.MvpPresenter

class UserPresenter : MvpPresenter<IUserView>() {

    private val router = GithubClientApp.instance?.getRouter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router?.exit()
        return true
    }

}