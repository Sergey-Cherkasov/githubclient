package br.svcdev.githubclient.model.repository

import br.svcdev.githubclient.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}