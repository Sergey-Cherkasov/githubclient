package br.svcdev.githubclient.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserView : MvpView {
    fun init()
    fun setLogin(login: String?)
    fun setAvatar(avatarUrl: String?)
    fun updateList()
}