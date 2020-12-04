package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.interfaces.IBackButtonListener
import br.svcdev.githubclient.databinding.FragmentRepoBinding
import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.presenter.RepoPresenter
import br.svcdev.githubclient.view.interfaces.IRepoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), IRepoView, IBackButtonListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"
        fun newInstance(repository: GithubRepo) = RepoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    lateinit var binding: FragmentRepoBinding
    private val presenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubRepo>(REPOSITORY_ARG) as GithubRepo
        RepoPresenter(repo).apply {
            GithubClientApp.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun setNumberForks(forksCount: Int) {
        binding.tvNumberForks.text = forksCount.toString()
    }
}