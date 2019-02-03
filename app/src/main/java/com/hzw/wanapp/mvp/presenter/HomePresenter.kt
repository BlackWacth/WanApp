package com.hzw.wanapp.mvp.presenter

import com.hzw.wanapp.ext.ss
import com.hzw.wanapp.mvp.bean.Article
import com.hzw.wanapp.mvp.bean.ArticleResponseBody
import com.hzw.wanapp.mvp.bean.HttpResult
import com.hzw.wanapp.mvp.contract.HomeContract
import com.hzw.wanapp.mvp.model.HomeModel
import com.hzw.wanapp.utils.SettingUtil
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class HomePresenter : CommonPresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter {

    override fun createModel() = HomeModel()

    override fun requestBanner() {
        mModel?.requestBanner()?.ss(mModel, mView, false) {
            mView?.setBanner(it.data)
        }
    }

    override fun requestArticles(num: Int) {
        mModel?.requestArticles(num)?.ss(mModel, mView, num == 0) {
            mView?.setArticles(it.data)
        }
    }

    override fun requestHomeData() {
        requestBanner()

        val observable = if (SettingUtil.getIsShowTopArticle()) {
            mModel?.requestArticles(0)
        } else {
            Observable.zip(
                mModel?.requestTopArticles(),
                mModel?.requestArticles(0),
                BiFunction<HttpResult<MutableList<Article>>, HttpResult<ArticleResponseBody>, HttpResult<ArticleResponseBody>> {
                    t1, t2 ->
                    t1.data.forEach {
                        it.top = "1"
                    }
                    t2.data.datas.addAll(0, t1.data)
                    t2
                })
        }
        observable?.ss(mModel, mView, false) {
            mView?.setArticles(it.data)
        }
    }
}