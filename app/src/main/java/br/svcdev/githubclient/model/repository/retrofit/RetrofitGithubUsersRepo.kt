package br.svcdev.githubclient.model.repository.retrofit

import br.svcdev.githubclient.model.api.interfaces.IUsersSource
import br.svcdev.githubclient.model.repository.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IUsersSource) : IGithubUsersRepo {
    override fun getUsers() = api.loadUsers().subscribeOn(Schedulers.io())
}