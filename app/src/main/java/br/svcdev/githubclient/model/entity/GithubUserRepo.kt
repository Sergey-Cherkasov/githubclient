package br.svcdev.githubclient.model.entity

import io.reactivex.rxjava3.core.Observable

class GithubUserRepo {
    private val repositories: List<GithubUser> = listOf(
            GithubUser("user1"),
            GithubUser("user2"),
            GithubUser("user3"),
            GithubUser("user4"),
            GithubUser("user5")
    )

    fun getUsers(): Observable<List<GithubUser>> = Observable.just(repositories)
}