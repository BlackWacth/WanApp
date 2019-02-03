package com.hzw.wanapp.utils

import android.graphics.Color
import android.preference.PreferenceManager
import com.hzw.wanapp.R
import com.hzw.wanapp.app.App

object SettingUtil {

    private val setting =  PreferenceManager.getDefaultSharedPreferences(App.context)

    /**
     * 获取是否开启显示首页置顶文章，true: 不显示  false: 显示
     */
    fun getIsShowTopArticle() = setting.getBoolean("switch_show_top", false)

    /**
     * 获取主题颜色
     */
    fun getColor(): Int {
        val defaultColor = App.context.resources.getColor(R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else color
    }

    fun setColor(color: Int) {
        setting.edit().putInt("color", color).apply()
    }
}