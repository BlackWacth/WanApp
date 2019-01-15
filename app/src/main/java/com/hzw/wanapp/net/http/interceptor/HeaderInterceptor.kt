package com.hzw.wanapp.net.http.interceptor

import com.hzw.wanapp.app.constant.HttpConstant
import com.hzw.wanapp.utils.Preference
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 设置请求头
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json; charset=utf-8")

        val host = request.url().host()
        val url = request.url().toString()

        if (host.isNotEmpty() && (url.contains(HttpConstant.COLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.UNCOLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.ARTICLE_WEBSITE)
                    || url.contains(HttpConstant.TODO_WEBSITE))
        ) {
            val spHost: String by Preference(host, "")
            val cookie: String = if (spHost.isNotEmpty()) spHost else ""
            if (cookie.isNotEmpty()) {
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }
        return chain.proceed(builder.build())
    }
}