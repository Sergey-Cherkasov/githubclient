package br.svcdev.githubclient.model.api.interfaces

import br.svcdev.githubclient.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IUsersSource {
    @GET("/users")
    fun loadUsers(): Single<List<GithubUser>>
}