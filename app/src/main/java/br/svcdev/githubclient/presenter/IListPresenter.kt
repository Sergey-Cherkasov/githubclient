package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.view.interfaces.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}