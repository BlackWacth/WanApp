package com.hzw.wanapp.mvp.base

import android.support.v7.app.AppCompatActivity
import com.hzw.wanapp.app.constant.Constant
import com.hzw.wanapp.receiver.NetworkChangeReceiver
import com.hzw.wanapp.utils.Preference
import com.hzw.wanapp.utils.SettingUtil


abstract class BaseActivity : AppCompatActivity() {

    protected var isLogin by Preference(Constant.LOGIN_KEY, false)

    /**
     * 缓存上一次网络状态
     */
    protected var hasNetwork by Preference(Constant.HAS_NETWORK_KEY, true)

    protected var mNetworkChangeReceiver: NetworkChangeReceiver? = null

    protected var mThemeColor: Int = SettingUtil.getColor()



}