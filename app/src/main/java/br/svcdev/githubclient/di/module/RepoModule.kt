package br.svcdev.githubclient.di.module

import br.svcdev.githubclient.common.interfaces.INetworkStatus
import br.svcdev.githubclient.model.api.interfaces.IDataSource
import br.svcdev.githubclient.model.cache.IGithubReposCache
import br.svcdev.githubclient.model.cache.IGithubUsersCache
import br.svcdev.githubclient.model.repository.IGithubReposRepo
import br.svcdev.githubclient.model.repository.IGithubUsersRepo
import br.svcdev.githubclient.model.repository.retrofit.RetrofitGithubReposRepo
import br.svcdev.githubclient.model.repository.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun usersRepo(api: IDataSource, status: INetworkStatus, cache: IGithubUsersCache):
            IGithubUsersRepo = RetrofitGithubUsersRepo(api, status, cache)

    @Provides
    @Singleton
    fun reposRepo(api: IDataSource, status: INetworkStatus, cache: IGithubReposCache):
            IGithubReposRepo = RetrofitGithubReposRepo(api, status, cache)
}