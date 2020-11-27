package br.svcdev.githubclient.model.cache.room

import br.svcdev.githubclient.model.cache.IGithubUsersCache
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database
import br.svcdev.githubclient.model.entity.room.RoomGithubUser

class RoomGithubUsersCache : IGithubUsersCache {
    override fun insert(db: Database, users: List<GithubUser>) {
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

    override fun getAll(db: Database): List<GithubUser> {
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

