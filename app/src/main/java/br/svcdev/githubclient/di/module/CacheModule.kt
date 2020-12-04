package br.svcdev.githubclient.di.module

import androidx.room.Room
import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.model.cache.IGithubReposCache
import br.svcdev.githubclient.model.cache.IGithubUsersCache
import br.svcdev.githubclient.model.cache.room.RoomGithubReposCache
import br.svcdev.githubclient.model.cache.room.RoomGithubUsersCache
import br.svcdev.githubclient.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(): Database = Room.databaseBuilder(
            GithubClientApp.instance,
            Database::class.java,
            Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)

    @Singleton
    @Provides
    fun reposCache(db: Database): IGithubReposCache = RoomGithubReposCache(db)

}