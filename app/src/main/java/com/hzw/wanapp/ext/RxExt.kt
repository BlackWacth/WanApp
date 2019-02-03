package com.hzw.wanapp.ext

import com.hzw.wanapp.R
import com.hzw.wanapp.app.App
import com.hzw.wanapp.mvp.base.IModel
import com.hzw.wanapp.mvp.base.IView
import com.hzw.wanapp.mvp.bean.BaseBean
import com.hzw.wanapp.net.http.exception.ErrorStatus
import com.hzw.wanapp.net.http.exception.ExceptionHandler
import com.hzw.wanapp.net.http.function.RetryWithDelay
import com.hzw.wanapp.rx.SchedulerUtils
import com.hzw.wanapp.utils.NetWorkUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun <T : BaseBean> Observable<T>.ss(
    mModel: IModel?,
    mView: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                if (isShowLoading) {
                    mView?.hideLoading()
                }
            }

            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) {
                    mView?.showLoading()
                }
                mModel?.addDisposable(d)
                if (!NetWorkUtil.isNetworkAvailable(App.instance)) {
                    mView?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
                    onComplete()
                }
            }

            override fun onNext(t: T) {
                when {
                    t.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(t)
                    t.errorCode == ErrorStatus.TOKEN_INVAILD -> {
                        mView?.showDefaultMsg("Token 过期，重新登录")
                    }
                    else -> mView?.showDefaultMsg(t.errorMsg)
                }
            }

            override fun onError(e: Throwable) {
                if (isShowLoading) {
                    mView?.hideLoading()
                }
                mView?.showError(ExceptionHandler.handleException(e))
            }

        })

}