package com.hzw.wanapp.mvp.presenter

import com.hzw.wanapp.mvp.base.BasePresenter
import com.hzw.wanapp.mvp.base.CommonContract

open class CommonPresenter<M: CommonContract.Model, V: CommonContract.View> : BasePresenter<M, V>(), CommonContract.Presenter<V>{

    override fun addCollectArticle(id: Int) {

    }

    override fun cancelCollectArticle(id: Int) {

    }


}