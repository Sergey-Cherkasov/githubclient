package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.repository.IGithubReposRepo
import br.svcdev.githubclient.view.interfaces.IRepoItemView
import br.svcdev.githubclient.view.interfaces.IUserView
import br.svcdev.githubclient.view.ui.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
        private val router: Router,
        private val reposRepo: IGithubReposRepo,
        private val mainThreadScheduler: Scheduler,
        private val user: GithubUser?
) : MvpPresenter<IUserView>() {

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        user?.login.let {
            viewState.setLogin(it)
        }
        user?.avatarUrl?.let {
            viewState.setAvatar(it)
        }
        loadData()
        reposListPresenter.itemClickListener = { itemView ->
            val repo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(Screens.RepoScreen(repo))
        }
    }

    private fun loadData() {
        user?.let {
            reposRepo.getRepos(it.login)
                    .observeOn(mainThreadScheduler)
                    .subscribe({ repos ->
                        reposListPresenter.repos.clear()
                        reposListPresenter.repos.addAll(repos)
                        viewState.updateList()
                    }, {
                        println("Error: ${it.message}")
                    })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    inner class ReposListPresenter : IReposListPresenter {
        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun bindView(view: IRepoItemView) {
            val repo = repos[view.pos]
            repo.name.let { view.setRepoName(it) }
            repo.description?.let { view.setRepoDescription(it) }
        }

        override fun getCount() = repos.size

    }

}