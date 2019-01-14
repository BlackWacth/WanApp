package com.hzw.wanapp.net.api

import com.hzw.wanapp.mvp.model.bean.Banner
import com.hzw.wanapp.mvp.model.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET

const val BASE_URL = "http://www.wanandroid.com/"

interface ApiService {

    @GET("banner/json")
    fun getBanners(): Observable<HttpResult<List<Banner>>>

}