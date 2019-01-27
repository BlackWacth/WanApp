package com.hzw.wanapp.mvp.contract

import com.hzw.wanapp.mvp.base.CommonContract
import com.hzw.wanapp.mvp.bean.Article
import com.hzw.wanapp.mvp.bean.ArticleResponseBody
import com.hzw.wanapp.mvp.bean.Banner
import com.hzw.wanapp.mvp.bean.HttpResult
import io.reactivex.Observable

interface HomeContract {

    interface View : CommonContract.View {
        fun scrollToTop()

        fun setBanner(banners: List<Banner>)

        fun setArticles(articles: ArticleResponseBody)
    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestBanner()

        fun requestHomeData()

        fun requestArticles(num: Int)
    }

    interface Model : CommonContract.Model {

        fun requestBanner(): Observable<HttpResult<List<Banner>>>

        fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>>

        fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>>
    }

}