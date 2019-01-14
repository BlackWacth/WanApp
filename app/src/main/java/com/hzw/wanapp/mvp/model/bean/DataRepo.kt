package com.hzw.wanapp.mvp.model.bean

import com.squareup.moshi.Json

open class BaseBean {
    var errorCode: Int = 0
    var errorMsg: String = ""
}

data class HttpResult<T>(@Json(name = "data") val data: T) : BaseBean()

/**
 * 轮播图
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)