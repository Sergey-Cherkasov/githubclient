package br.svcdev.githubclient.tests

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function4
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operators {
    companion object {
        private val TAG = Operators::class.simpleName

        fun exec() {
            Consumer(Producer()).exec()
        }
    }

    class Producer {
        fun just(): Observable<String> = Observable.just("a", "b", "c", "c")
        fun just2(): Observable<String> = Observable.just("d", "e", "f")

    }

    class Consumer(private val producer: Producer) {
        private fun execTake() {
            producer.just().take(2).subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execSkip() {
            producer.just().skip(2).subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execMap() {
            producer.just().map { s -> s + s }.subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execDistinct() {
            producer.just().distinct().subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execFilter() {
            producer.just().distinct().filter { s -> s != "b" }.subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execMerge() {
            producer.just().distinct().mergeWith(producer.just2()).subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execFlatMap() {
            producer.just()
                    .flatMap {
                        val random = Random.nextInt(1000).toLong()
                        return@flatMap Observable.just(it + "x").delay(random, TimeUnit.MILLISECONDS)
                    }.subscribe(
                            { s -> Log.i(TAG, "onNext $s") },
                            { e -> Log.i(TAG, "onError $e") },
                            { Log.i(TAG, "onComplete") }
                    )
        }

        private fun execSwitchMap() {
            producer.just()
                    .switchMap {
                        val random = Random.nextInt(1000).toLong()
                        return@switchMap Observable.just(it + "x").delay(random, TimeUnit.MILLISECONDS)
                    }.subscribe(
                            { s -> Log.i(TAG, "onNext $s") },
                            { e -> Log.i(TAG, "onError $e") },
                            { Log.i(TAG, "onComplete") }
                    )
        }

        private fun execZip() {
            val observable1 = Observable.just("1", "5").delay(1, TimeUnit.SECONDS)
            val observable2 = Observable.just("2", "6").delay(2, TimeUnit.SECONDS)
            val observable3 = Observable.just("3", "7").delay(3, TimeUnit.SECONDS)
            val observable4 = Observable.just("4", "8").delay(4, TimeUnit.SECONDS)

            Observable.zip(observable1, observable2, observable3, observable4,
                    Function4<String, String, String, String, List<String>> { o, o2, o3, o4 ->
                        return@Function4 listOf(o, o2, o3, o4)
                    }).subscribeOn(Schedulers.computation())
                    .subscribe(
                            { s -> Log.i(TAG, "onNext $s") },
                            { e -> Log.i(TAG, "onError ${e.message}") },
                            { Log.i(TAG, "onComplete") }
                    )
        }

        fun exec() {
            execTake()
            execSkip()
            execMap()
            execDistinct()
            execFilter()
            execMerge()
            execFlatMap()
            execSwitchMap()
            execZip()
        }
    }
}