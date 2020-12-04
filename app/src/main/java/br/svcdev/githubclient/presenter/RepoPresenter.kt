package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.view.interfaces.IRepoView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoPresenter(private val repo: GithubRepo?) : MvpPresenter<IRepoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.forksCount?.let {
            viewState.setNumberForks(it)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}