package com.hzw.wanapp.mvp.model

import com.hzw.wanapp.mvp.base.CommonModel
import com.hzw.wanapp.mvp.bean.Article
import com.hzw.wanapp.mvp.bean.ArticleResponseBody
import com.hzw.wanapp.mvp.bean.Banner
import com.hzw.wanapp.mvp.bean.HttpResult
import com.hzw.wanapp.mvp.contract.HomeContract
import com.hzw.wanapp.net.http.RetrofitHelper
import io.reactivex.Observable

class HomeModel : CommonModel(), HomeContract.Model {

    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> {
        return RetrofitHelper.service.getTopArticles()
    }

    override fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticles(num)
    }


}