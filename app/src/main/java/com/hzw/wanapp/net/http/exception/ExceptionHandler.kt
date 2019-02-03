package com.hzw.wanapp.net.http.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExceptionHandler {

    companion object {
        private const val TAG = "ExceptionHandler"
        var mErrorCode = ErrorStatus.UNKNOWN_ERROR
        var mErrorMsg = "请求失败，请稍后重试"

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            when {
                (e is SocketTimeoutException || e is ConnectException || e is HttpException) -> {
                    Logger.e(TAG, "网络连接异常: " + e.message)
                    mErrorCode = ErrorStatus.NETWORK_ERROR
                    mErrorMsg = "网络连接异常"
                }

                (e is JsonParseException || e is JSONException || e is ParseException) -> {
                    Logger.e(TAG, "数据解析异常: " + e.message)
                    mErrorCode = ErrorStatus.SERVER_ERROR
                    mErrorMsg = "数据解析异常"
                }

                e is ApiException -> {
                    Logger.e(TAG, "服务器返回信息错误(API): " + e.message)
                    mErrorCode = ErrorStatus.SERVER_ERROR
                    mErrorMsg = e.message.toString()
                }

                e is UnknownHostException -> {
                    Logger.e(TAG, "无法解析该域名: " + e.message)
                    mErrorCode = ErrorStatus.NETWORK_ERROR
                    mErrorMsg = "无法解析该域名"
                }

                e is IllegalArgumentException -> {
                    Logger.e(TAG, "参数错误: " + e.message)
                    mErrorCode = ErrorStatus.SERVER_ERROR
                    mErrorMsg = "参数错误"
                }

                else -> {
                    Logger.e(TAG, "未知错误: " + e.message)
                    mErrorCode = ErrorStatus.UNKNOWN_ERROR
                    mErrorMsg = "未知错误"
                }
            }

            return mErrorMsg;
        }
    }

}