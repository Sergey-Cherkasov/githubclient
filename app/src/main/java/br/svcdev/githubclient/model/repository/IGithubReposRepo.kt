package br.svcdev.githubclient.model.repository

import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubReposRepo {
    fun getRepos(user: GithubUser): Single<List<GithubRepo>>
}