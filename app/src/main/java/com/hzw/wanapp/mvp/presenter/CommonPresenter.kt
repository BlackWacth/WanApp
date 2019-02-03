package com.hzw.wanapp.mvp.presenter

import com.hzw.wanapp.ext.ss
import com.hzw.wanapp.mvp.base.BasePresenter
import com.hzw.wanapp.mvp.contract.CommonContract

open class CommonPresenter<M : CommonContract.Model, V : CommonContract.View> : BasePresenter<M, V>(),
    CommonContract.Presenter<V> {

    override fun addCollectArticle(id: Int) {
        mModel?.addCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCollectSuccess(true)
        }
    }

    override fun cancelCollectArticle(id: Int) {
        mModel?.cancelCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCancelCollectSucdess(true)
        }
    }
}