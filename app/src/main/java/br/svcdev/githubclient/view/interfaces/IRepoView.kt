package br.svcdev.githubclient.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IRepoView : MvpView {
    fun setNumberForks(forksCount: Int)
}