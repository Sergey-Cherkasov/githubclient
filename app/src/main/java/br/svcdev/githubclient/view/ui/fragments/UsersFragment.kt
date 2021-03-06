package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.interfaces.IBackButtonListener
import br.svcdev.githubclient.databinding.FragmentUsersBinding
import br.svcdev.githubclient.presenter.UsersPresenter
import br.svcdev.githubclient.view.image.GlideImageLoader
import br.svcdev.githubclient.view.interfaces.IUsersView
import br.svcdev.githubclient.view.ui.adapters.UsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
            GithubClientApp.instance.appComponent.inject(this)
        }
    }

    private lateinit var adapter: UsersRVAdapter

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var binding: FragmentUsersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

}