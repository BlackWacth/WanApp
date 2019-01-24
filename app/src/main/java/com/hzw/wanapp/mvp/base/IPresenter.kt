package com.hzw.wanapp.mvp.base

interface IPresenter<in V : IView> {

    /**
     * 绑定View
     */
    fun attachView(view: V)

    /**
     * 解绑View
     */
    fun detachView()
}