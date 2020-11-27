package br.svcdev.githubclient.model.repository.retrofit

import br.svcdev.githubclient.common.interfaces.INetworkStatus
import br.svcdev.githubclient.model.api.interfaces.IReposSource
import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database
import br.svcdev.githubclient.model.entity.room.RoomGithubRepository
import br.svcdev.githubclient.model.repository.IGithubReposRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(
        private val api: IReposSource,
        private val networkStatus: INetworkStatus,
        private val db: Database)
    : IGithubReposRepo {

    override fun getRepos(user: GithubUser): Single<List<GithubRepo>> =
            networkStatus.isOnlineSingle().flatMap { isOnline ->
                if (isOnline) {
                    user.login?.let { userLogin ->
                        api.loadRepos(userLogin)
                                .flatMap { repositories ->
                                    Single.fromCallable {
                                        val roomUser = userLogin.let {
                                            db.userDao.findByLogin(it)
                                        } ?: throw RuntimeException("No such user in cache")

                                        val roomRepos = repositories.map {
                                            RoomGithubRepository(
                                                    it.id ?: 0,
                                                    it.name ?: "",
                                                    it.description ?: "",
                                                    it.forksCount ?: 0,
                                                    roomUser.id)
                                        }
                                        db.repositoryDao.insert(roomRepos)
                                        repositories
                                    }
                                }
                    } ?: Single.error<List<GithubRepo>>(RuntimeException("User has no repos url"))
                            .subscribeOn(Schedulers.io())
                } else {
                    Single.fromCallable {
                        val roomUser = user.login?.let {
                            db.userDao.findByLogin(it)
                        } ?: throw RuntimeException("No such user in cache")
                        db.repositoryDao.findForUser(roomUser.id).map {
                            GithubRepo(
                                    it.id,
                                    it.name,
                                    it.descroption,
                                    it.forksCount)
                        }
                    }

                }
            }.subscribeOn(Schedulers.io())
}