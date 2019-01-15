package com.hzw.wanapp.app.constant

import com.hzw.wanapp.utils.Preference
import java.lang.StringBuilder

object HttpConstant {

    const val DEFAULT_TIMEOUT: Long = 15

    const val SAVE_USER_LOGIN_KEY = "user/login"
    const val SAVE_USER_REGISTER_KEY = "user/register"
    const val SET_COOKIE_KEY = "set-cookie"

    const val COLLECTIONS_WEBSITE = "lg/collect"
    const val UNCOLLECTIONS_WEBSITE = "lg/uncollect"
    const val ARTICLE_WEBSITE = "article"
    const val TODO_WEBSITE = "lg/todo"

    /** 50MB 的缓存大小 */
    const val MAX_CACHE_SIZE: Long = 1024 * 102 * 50
    const val COOKIE_NAME: String = "Cookie"

    fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()

        cookies.map { cookie ->
            cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }.forEach { it ->
            it.filterNot { set.contains(it) }.forEach { set.add(it) }
        }

        val iterator = set.iterator()
        while (iterator.hasNext()) {
            val cookie = iterator.next()
            sb.append(cookie).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }

    fun saveCookie(url: String?, host: String?, cookies: String) {
        url ?: return
        var spUrl: String by Preference(url, cookies)
        spUrl = cookies
        host ?: return
        var spHost: String by Preference(host, cookies)
        spHost = cookies
    }

}