package br.svcdev.githubclient.model.api.interfaces

import br.svcdev.githubclient.model.entity.GithubRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IReposSource {
    @GET("/users/{user_name}/repos")
    fun loadRepos(@Path("user_name") userName: String): Single<List<GithubRepo>>
}