package br.svcdev.githubclient.model

import java.util.*

class Model {
    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrentValue(index: Int): Int {
        return counters[index]
    }

    fun incrementValue(index: Int): Int {
        counters[index] ++
        return getCurrentValue(index)
    }

    fun setCounterValue(index: Int, value: Int) {
        counters[index] = value
    }

}