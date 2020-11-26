package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.interfaces.IBackButtonListener
import br.svcdev.githubclient.databinding.FragmentRepoBinding
import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.presenter.RepoPresenter
import br.svcdev.githubclient.view.interfaces.IRepoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment(repo: GithubRepo?) : MvpAppCompatFragment(), IRepoView, IBackButtonListener {
    lateinit var binding: FragmentRepoBinding
    private val presenter by moxyPresenter {
        RepoPresenter(GithubClientApp.instance.getRouter(),
                repo)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    fun getInstance(repo: GithubRepo?): Fragment {
        return RepoFragment(repo)
    }

    override fun setNumberForks(forksCount: Int) {
        binding.tvNumberForks.text = forksCount.toString()
    }
}