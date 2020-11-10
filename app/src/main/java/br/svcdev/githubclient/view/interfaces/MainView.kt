package br.svcdev.githubclient.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle //Стратегия Moxy: Выполнить команду, добавить ее в конец очереди и удалить все ее предыдущие экземпляры
interface MainView : MvpView {
}