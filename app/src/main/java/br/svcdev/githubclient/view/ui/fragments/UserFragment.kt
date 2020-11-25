package br.svcdev.githubclient.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.svcdev.githubclient.databinding.FragmentUserBinding
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.presenter.UserPresenter
import br.svcdev.githubclient.view.interfaces.IBackButtonListener
import br.svcdev.githubclient.view.interfaces.IUserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), IUserView, IBackButtonListener {

    private lateinit var binding: FragmentUserBinding
    private val presenter by moxyPresenter { UserPresenter() }
    private lateinit var user: GithubUser

    fun getInstance(data: GithubUser): UserFragment {
        val fragment = UserFragment()
        val bundle = Bundle()
        bundle.putParcelable("user", data)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            // запоминаем аргументы
            user = it.getParcelable("user")!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init() {
        binding.tvLogin.text = user.login
    }

}