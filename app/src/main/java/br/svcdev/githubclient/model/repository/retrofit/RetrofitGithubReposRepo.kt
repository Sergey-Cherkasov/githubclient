package br.svcdev.githubclient.model.repository.retrofit

import br.svcdev.githubclient.common.interfaces.INetworkStatus
import br.svcdev.githubclient.model.api.interfaces.IDataSource
import br.svcdev.githubclient.model.cache.IGithubReposCache
import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.repository.IGithubReposRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(
        private val api: IDataSource,
        private val networkStatus: INetworkStatus,
        private val reposCache: IGithubReposCache)
    : IGithubReposRepo {

    override fun getRepos(user: GithubUser): Single<List<GithubRepo>> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    user.login?.let { userLogin ->
                        api.loadRepos(userLogin)
                                .flatMap { repositories ->
                                    Single.fromCallable {
                                        reposCache.insert(userLogin, repositories)
                                        repositories
                                    }
                                }
                    } ?: Single.error<List<GithubRepo>>(RuntimeException("User has no repos url"))
                            .subscribeOn(Schedulers.io())
                } else {
                    Single.fromCallable {
                        reposCache.getAll(user)
                    }

                }
            }.subscribeOn(Schedulers.io())
}