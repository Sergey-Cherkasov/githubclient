package br.svcdev.githubclient.model.cache.room

import br.svcdev.githubclient.model.cache.IGithubReposCache
import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database
import br.svcdev.githubclient.model.entity.room.RoomGithubRepository

class RoomGithubReposCache(val db: Database) : IGithubReposCache {

    override fun insert(userLogin: String, repositories: List<GithubRepo>) {
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
    }

    override fun getAll(user: GithubUser): List<GithubRepo> {
        val roomUser = user.login?.let {
            db.userDao.findByLogin(it)
        } ?: throw RuntimeException("No such user in cache")
        return db.repositoryDao.findForUser(roomUser.id).map {
            GithubRepo(
                    it.id,
                    it.name,
                    it.descroption,
                    it.forksCount)
        }
    }
}