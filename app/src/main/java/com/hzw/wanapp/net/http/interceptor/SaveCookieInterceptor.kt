package com.hzw.wanapp.net.http.interceptor

import com.hzw.wanapp.app.constant.HttpConstant
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 保存cookie
 */
class SaveCookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val url = request.url().toString()
        val host = request.url().host()

        if ((url.contains(HttpConstant.SAVE_USER_LOGIN_KEY)
                    || url.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
            && !response.headers(HttpConstant.SET_COOKIE_KEY).isEmpty()
        ) {
            val cookies = response.headers(HttpConstant.SET_COOKIE_KEY)
            val cookie = HttpConstant.encodeCookie(cookies)
            Logger.i("cookies = $cookies \n 处理后的 cookie = $cookie")
            HttpConstant.saveCookie(url, host, cookie)
        }
        return response
    }
}