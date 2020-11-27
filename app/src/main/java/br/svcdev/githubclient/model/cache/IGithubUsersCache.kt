package br.svcdev.githubclient.model.cache

import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database

interface IGithubUsersCache {
    fun insert(db: Database, users: List<GithubUser>)
    fun getAll(db: Database): List<GithubUser>
}