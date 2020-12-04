package br.svcdev.githubclient.di.module

import br.svcdev.githubclient.GithubClientApp
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@Module
class AppModule(private val app: GithubClientApp) {
    @Provides
    fun app(): GithubClientApp = app

    @Provides
    fun mainThreadScheduler() = AndroidSchedulers.mainThread()
}