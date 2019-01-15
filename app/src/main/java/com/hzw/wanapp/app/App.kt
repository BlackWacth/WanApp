package com.hzw.wanapp.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlin.properties.Delegates

class App : MultiDexApplication() {

    companion object {
        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        initLoggerConfig()
    }

    private fun initLoggerConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag("hua_zw")
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}