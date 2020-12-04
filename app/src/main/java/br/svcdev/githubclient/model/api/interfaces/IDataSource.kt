package br.svcdev.githubclient.model.api.interfaces

import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET("/users/{user_login}/repos")
    fun loadRepos(@Path("user_login") userLogin: String): Single<List<GithubRepo>>

}