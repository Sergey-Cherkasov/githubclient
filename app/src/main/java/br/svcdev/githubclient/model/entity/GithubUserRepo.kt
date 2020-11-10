package br.svcdev.githubclient.model.entity

import java.util.*

class GithubUserRepo {
    private val repositories: List<GithubUser> = listOf(
            GithubUser("user1"),
            GithubUser("user2"),
            GithubUser("user3"),
            GithubUser("user4"),
            GithubUser("user5")
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}