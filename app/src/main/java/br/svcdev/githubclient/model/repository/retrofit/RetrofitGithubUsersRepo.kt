package br.svcdev.githubclient.model.repository.retrofit

import br.svcdev.githubclient.common.interfaces.INetworkStatus
import br.svcdev.githubclient.model.api.interfaces.IDataSource
import br.svcdev.githubclient.model.cache.IGithubUsersCache
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.repository.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
        private val api: IDataSource,
        private val networkStatus: INetworkStatus,
        private val usersCache: IGithubUsersCache)
    : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    api.loadUsers().flatMap { users ->
                        Single.fromCallable {
                            usersCache.insert(users)
                            users
                        }
                    }
                } else {
                    Single.fromCallable {
                        usersCache.getAll()
                    }
                }
            }.subscribeOn(Schedulers.io())
}