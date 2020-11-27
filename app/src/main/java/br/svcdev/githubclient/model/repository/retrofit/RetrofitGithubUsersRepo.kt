package br.svcdev.githubclient.model.repository.retrofit

import br.svcdev.githubclient.common.interfaces.INetworkStatus
import br.svcdev.githubclient.model.api.interfaces.IUsersSource
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database
import br.svcdev.githubclient.model.entity.room.RoomGithubUser
import br.svcdev.githubclient.model.repository.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
        private val api: IUsersSource,
        private val networkStatus: INetworkStatus,
        private val db: Database)
    : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.loadUsers()
                    .flatMap { users ->
                        Single.fromCallable {
                            val roomUsers = users.map { user -> RoomGithubUser(
                                    user.id ?: 0,
                                    user.login ?: "",
                                    user.avatarUrl ?: "",
                                    user.reposUrl ?: ""
                            ) }
                            db.userDao.insert(roomUsers)
                            users
                        }
                    }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(
                            roomUser.id,
                            roomUser.login,
                            roomUser.avatarUrl,
                            roomUser.reposUrl
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
    }