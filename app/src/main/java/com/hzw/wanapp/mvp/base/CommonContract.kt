package com.hzw.wanapp.mvp.base

import com.hzw.wanapp.mvp.bean.HttpResult
import io.reactivex.Observable

interface CommonContract {

    interface View : IView {
        fun showCollectSuccess(success: Boolean)

        fun showCancelCollectSucdess(success: Boolean)
    }

    interface Presenter<in V : View> : IPresenter<V> {
        fun addCollectArticle(id: Int)

        fun cancelCollectArticle(id: Int)
    }

    interface Model : IModel {
        fun addCollectArticle(id: Int): Observable<HttpResult<Any>>

        fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>>
    }
}