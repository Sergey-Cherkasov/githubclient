package br.svcdev.githubclient.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUsersView : MvpView {
    fun init()
    fun updateList()
}