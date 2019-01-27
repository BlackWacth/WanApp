package com.hzw.wanapp.mvp.base

import com.hzw.wanapp.mvp.bean.HttpResult
import com.hzw.wanapp.net.http.RetrofitHelper
import io.reactivex.Observable

open class CommonModel : BaseModel(), CommonContract.Model {
    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addCollectArticle(id)
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.cancelCollectArticle(id)
    }
}