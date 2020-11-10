package br.svcdev.githubclient.view.ui.activities

import android.os.Bundle
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.R
import br.svcdev.githubclient.presenter.MainPresenter
import br.svcdev.githubclient.view.interfaces.IBackButtonListener
import br.svcdev.githubclient.view.interfaces.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private val presenter by moxyPresenter { MainPresenter() }
    private val navigatorHolder = GithubClientApp.instance?.getNavigatorHolder()
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder?.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        navigatorHolder?.removeNavigator()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is IBackButtonListener && fragment.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}