package com.hzw.wanapp.net.http.function

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * 重连
 */
class RetryWithDelay(private val mMaxRetryCount: Int = 3, private val mRetryDelayMillis: Long = 3000L) :
    Function<Observable<out Throwable>, Observable<*>> {

    override fun apply(observable: Observable<out Throwable>): Observable<*> {
        return observable.zipWith(
            Observable.range(1, mMaxRetryCount + 1),
            BiFunction<Throwable, Int, Wrapper> { t1, t2 ->
                Wrapper(t2, t1)
            })
            .flatMap { wrapper ->
                val t = wrapper.throwable
                if ((t is ConnectException || t is SocketTimeoutException || t is TimeoutException || t is HttpException) && wrapper.index < mMaxRetryCount + 1) {
                    Observable.timer(mRetryDelayMillis * wrapper.index, TimeUnit.MILLISECONDS)
                } else {
                    Observable.error(t)
                }

            }
    }

    private inner class Wrapper(val index: Int, val throwable: Throwable)

}