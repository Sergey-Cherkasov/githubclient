package br.svcdev.githubclient.view.interfaces

interface IUserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}