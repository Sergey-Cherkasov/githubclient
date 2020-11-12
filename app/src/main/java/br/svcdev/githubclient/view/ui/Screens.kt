package br.svcdev.githubclient.view.ui

import androidx.fragment.app.Fragment
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.view.ui.fragments.UserFragment
import br.svcdev.githubclient.view.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return UsersFragment()
        }
    }

    class UserScreen(val user: GithubUser) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return UserFragment().getInstance(user)
        }
    }
}