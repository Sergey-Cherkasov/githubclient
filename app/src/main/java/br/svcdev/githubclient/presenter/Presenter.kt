package br.svcdev.githubclient.presenter

import br.svcdev.githubclient.model.ButtonIndexes
import br.svcdev.githubclient.model.Model
import br.svcdev.githubclient.view.MainView

class Presenter(private val view: MainView) {
    private val model = Model()

    fun onButtonOneClick() {
        view.setButtonOneText(model.incrementValue(ButtonIndexes.ONE).toString())
    }

    fun onButtonTwoClick() {
        view.setButtonTwoText(model.incrementValue(ButtonIndexes.TWO).toString())
    }

    fun onButtonThreeClick() {
        view.setButtonThreeText(model.incrementValue(ButtonIndexes.THREE).toString())
    }
}