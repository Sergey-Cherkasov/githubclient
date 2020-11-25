package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.view.interfaces.MainView
import br.svcdev.githubclient.view.ui.Screens
import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {
    private val router = GithubClientApp.instance?.getRouter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router?.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router?.exit()
    }
}