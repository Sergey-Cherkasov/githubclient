package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.interfaces.IBackButtonListener
import br.svcdev.githubclient.databinding.FragmentUserBinding
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.presenter.UserPresenter
import br.svcdev.githubclient.view.image.GlideImageLoader
import br.svcdev.githubclient.view.interfaces.IUserView
import br.svcdev.githubclient.view.ui.adapters.ReposRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), IUserView, IBackButtonListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply { putParcelable(USER_ARG, user) }
        }
    }

    private lateinit var binding: FragmentUserBinding

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(AndroidSchedulers.mainThread(), user).apply {
            GithubClientApp.instance.appComponent.inject(this)
        }
    }

    private val imageLoader = GlideImageLoader()
    private var adapter: ReposRVAdapter? = null

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
        binding.rvReposList.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        binding.rvReposList.adapter = adapter
    }

    override fun setLogin(login: String?) {
        binding.tvLogin.text = login
    }

    override fun setAvatar(avatarUrl: String?) {
        with(binding.ivAvatar) { avatarUrl?.let { imageLoader.loadInto(it, this) } }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

}