package br.svcdev.githubclient.view.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}