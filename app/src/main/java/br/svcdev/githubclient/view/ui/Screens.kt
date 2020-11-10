package br.svcdev.githubclient.view.ui

import androidx.fragment.app.Fragment
import br.svcdev.githubclient.view.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return UsersFragment()
        }
    }
}