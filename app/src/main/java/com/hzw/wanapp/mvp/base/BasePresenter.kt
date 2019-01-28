package com.hzw.wanapp.mvp.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleObserver {

    protected var mModel: M? = null
    protected var mView: V? = null

    private val isViewAttached: Boolean
        get() = mView != null


    private var mCompositeDisposable: CompositeDisposable? = null

    open fun createModel(): M? = null

    open fun useEventBus(): Boolean = false

    override fun attachView(view: V) {
        mView = view
        mModel = createModel()
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                (view as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun detachView() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        unDispose()
        mModel?.onDetach()
        mModel = null
        mView = null
        mCompositeDisposable = null
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()
        mCompositeDisposable = null
    }

    open fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }


    open fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before requesting data to the IPresenter")
}