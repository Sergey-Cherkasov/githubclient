package br.svcdev.githubclient

import android.app.Application
import br.svcdev.githubclient.common.AndroidNetworkStatus
import br.svcdev.githubclient.model.entity.room.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class GithubClientApp : Application() {

    private lateinit var cicerone: Cicerone<Router>

    companion object {
        lateinit var instance: GithubClientApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initCicerone()
        Database.create(instance)
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getRouter(): Router = cicerone.router
    fun getNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}