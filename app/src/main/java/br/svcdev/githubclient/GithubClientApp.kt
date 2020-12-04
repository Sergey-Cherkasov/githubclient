package br.svcdev.githubclient

import android.app.Application
import br.svcdev.githubclient.di.DaggerIAppComponent
import br.svcdev.githubclient.di.IAppComponent
import br.svcdev.githubclient.di.module.AppModule

class GithubClientApp : Application() {

    companion object {
        lateinit var instance: GithubClientApp
    }

    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerIAppComponent.builder().appModule(AppModule(this)).build()
    }
}