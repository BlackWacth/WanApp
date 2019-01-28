package com.hzw.wanapp.ext

import com.hzw.wanapp.mvp.base.IModel
import com.hzw.wanapp.mvp.base.IView
import com.hzw.wanapp.mvp.bean.BaseBean
import io.reactivex.Observable

fun<T: BaseBean> Observable<T>.ss(model: IModel?, view: IView, isShowLoading: Boolean = true, onSuccess: (T)->Unit) {

}