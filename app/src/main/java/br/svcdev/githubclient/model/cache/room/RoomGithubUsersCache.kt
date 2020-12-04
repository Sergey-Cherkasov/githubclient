package br.svcdev.githubclient.model.cache.room

import br.svcdev.githubclient.model.cache.IGithubUsersCache
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database
import br.svcdev.githubclient.model.entity.room.RoomGithubUser

class RoomGithubUsersCache(val db: Database) : IGithubUsersCache {

    override fun insert(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                    user.id ?: 0,
                    user.login ?: "",
                    user.avatarUrl ?: "",
                    user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun getAll(): List<GithubUser> {
        return db.userDao.getAll().map { roomUser ->
            GithubUser(
                    roomUser.id,
                    roomUser.login,
                    roomUser.avatarUrl,
                    roomUser.reposUrl
            )
        }
    }
}

