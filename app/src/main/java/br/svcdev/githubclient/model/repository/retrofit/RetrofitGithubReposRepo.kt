package br.svcdev.githubclient.model.repository.retrofit

import br.svcdev.githubclient.model.api.interfaces.IReposSource
import br.svcdev.githubclient.model.entity.GithubRepo
import br.svcdev.githubclient.model.repository.IGithubReposRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(private val api: IReposSource) :
        IGithubReposRepo {
    override fun getRepos(userName: String): Single<List<GithubRepo>> =
            api.loadRepos(userName).subscribeOn(Schedulers.io())
}