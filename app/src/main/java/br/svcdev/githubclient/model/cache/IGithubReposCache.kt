package br.svcdev.githubclient.model.cache

import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.model.entity.room.Database

interface IGithubReposCache {
    fun insert(db: Database, userLogin: String, repositories: List<GithubRepo>)
    fun getAll(db: Database, user: GithubUser): List<GithubRepo>
}