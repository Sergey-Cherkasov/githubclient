package br.svcdev.githubclient.model.repository

import br.svcdev.githubclient.model.entity.GithubRepo
import io.reactivex.rxjava3.core.Single

interface IGithubReposRepo {
    fun getRepos(userName: String): Single<List<GithubRepo>>
}