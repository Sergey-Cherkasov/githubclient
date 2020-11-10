package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.githubclient.databinding.FragmentUsersBinding
import br.svcdev.githubclient.presenter.UsersPresenter
import br.svcdev.githubclient.view.interfaces.IBackButtonListener

import br.svcdev.githubclient.view.interfaces.IUsersView
import br.svcdev.githubclient.view.ui.adapters.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, IBackButtonListener {

    private lateinit var adapter: UsersRVAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var binding: FragmentUsersBinding
    private val presenter by moxyPresenter { UsersPresenter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun init() {
        layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

}