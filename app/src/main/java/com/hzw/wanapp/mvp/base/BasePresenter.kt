package com.hzw.wanapp.mvp.base

import android.arch.lifecycle.LifecycleObserver

abstract class BasePresenter<M: IModel, V: IView> : IPresenter<V>, LifecycleObserver{
    override fun attachView(view: V) {

    }

    override fun detachView() {

    }
}