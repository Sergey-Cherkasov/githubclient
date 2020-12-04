package br.svcdev.githubclient.di

import br.svcdev.githubclient.di.module.*
import br.svcdev.githubclient.presenter.MainPresenter
import br.svcdev.githubclient.presenter.RepoPresenter
import br.svcdev.githubclient.presenter.UserPresenter
import br.svcdev.githubclient.presenter.UsersPresenter
import br.svcdev.githubclient.view.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApiModule::class,
            AppModule::class,
            CacheModule::class,
            CiceroneModule::class,
            RepoModule::class
        ]
)
interface IAppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)
}