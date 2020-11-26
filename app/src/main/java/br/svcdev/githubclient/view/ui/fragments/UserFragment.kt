package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.interfaces.IBackButtonListener
import br.svcdev.githubclient.databinding.FragmentUserBinding
import br.svcdev.githubclient.model.api.objects.ApiRepos
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.repository.retrofit.RetrofitGithubReposRepo
import br.svcdev.githubclient.presenter.UserPresenter
import br.svcdev.githubclient.view.image.GlideImageLoader
import br.svcdev.githubclient.view.interfaces.IUserView
import br.svcdev.githubclient.view.ui.adapters.ReposRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(private val user: GithubUser?) :
        MvpAppCompatFragment(), IUserView, IBackButtonListener {

    private lateinit var binding: FragmentUserBinding
    private val presenter by moxyPresenter {
        UserPresenter(
                GithubClientApp.instance.getRouter(),
                RetrofitGithubReposRepo(ApiRepos.api),
                AndroidSchedulers.mainThread(),
                user
        )
    }
    private val imageLoader = GlideImageLoader()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ReposRVAdapter

    fun getInstance(data: GithubUser): UserFragment {

        return UserFragment(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init() {
        layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        binding.rvReposList.layoutManager = layoutManager
        binding.rvReposList.adapter = adapter
    }

    override fun setLogin(login: String?) {
        binding.tvLogin.text = login
    }

    override fun setAvatar(avatarUrl: String?) {
        with(binding.ivAvatar) { avatarUrl?.let { imageLoader.loadInto(it, this) } }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

}