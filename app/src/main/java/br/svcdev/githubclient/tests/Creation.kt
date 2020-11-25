package br.svcdev.githubclient.tests

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit

class Creation {

    companion object {
        private val TAG = Creation::class.simpleName

        fun exec() {
            Consumer(Producer()).exec()
        }
    }

    class Producer {
        @Suppress("LABEL_NAME_CLASH")
        fun create(): Observable<String> = Observable.create { emitter ->
            try {
                for (i in 0..10) {
                    randomResult().let {
                        if (it) {
                            emitter.onNext("Success")
                            emitter.onComplete()
                            return@create
                        } else {
                            emitter.onError(RuntimeException("Error!"))
                            return@create
                        }
                    }
                }
            } catch (t: Throwable) {
                emitter.onError(RuntimeException("Error!"))
            }
        }

        fun just(): Observable<String> = Observable.just("a", "b", "c", "d")

        fun fromIterable(): Observable<String> = Observable.fromIterable(listOf("a", "b", "c", "d"))

        fun interval(): Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)

        fun timer(): Observable<Long> = Observable.timer(5, TimeUnit.SECONDS)

        fun range(): Observable<Int> = Observable.range(1, 10)

        fun fromCallable(): Observable<Boolean> = Observable.fromCallable { randomResult() }

        private fun randomResult(): Boolean {
            Thread.sleep(1000)
            return listOf(true, false, true)[Random().nextInt(2)]
        }
    }


    class Consumer(private val producer: Producer) {
        private val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(s: String?) {
                Log.i(TAG, "onNext $s")
            }

            override fun onError(e: Throwable?) {
                Log.i(TAG, "onError")
            }
        }

        private fun execJust() {
            producer.just().subscribe(stringObserver)
        }

        private fun execLambda() {
            producer.just().subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        private fun execFromIterable() {
            producer.fromIterable().subscribe(stringObserver)
        }

        private fun execInterval() {
            producer.interval().subscribe { s ->
                Log.i(TAG, "onNext $s")
            }
        }

        private fun execTimer() {
            producer.timer().subscribe { s -> Log.i(TAG, "onNext $s") }
        }

        private fun execRange() {
            producer.range().subscribe { s -> Log.i(TAG, "onNext $s") }
        }

        private fun execFromCallable() {
            producer.fromCallable().subscribe { s -> Log.i(TAG, "onNext $s") }
        }

        private fun execCreate() {
            producer.create().subscribe(
                    { s -> Log.i(TAG, "onNext $s") },
                    { e -> Log.i(TAG, "onError $e") },
                    { Log.i(TAG, "onComplete") }
            )
        }

        fun exec() {
            execJust()
            execLambda()
            execFromIterable()
            execInterval()
            execTimer()
            execRange()
            execFromCallable()
            execCreate()
        }
    }
}