package br.svcdev.githubclient.view.interfaces

interface IRepoItemView : IItemView {
    fun setRepoDescription(text: String?)
    fun setRepoName(text: String?)
}