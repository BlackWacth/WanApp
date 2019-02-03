package com.hzw.wanapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hzw.wanapp.app.constant.Constant
import com.hzw.wanapp.event.NetworkChangeEvent
import com.hzw.wanapp.utils.NetWorkUtil
import com.hzw.wanapp.utils.Preference
import org.greenrobot.eventbus.EventBus

class NetworkChangeReceiver : BroadcastReceiver(){

    var hasNetwork by Preference(Constant.HAS_NETWORK_KEY, true)

    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = NetWorkUtil.isNetworkConnected(context)
        if (isConnected) {
            if (isConnected != hasNetwork) {
                EventBus.getDefault().post(NetworkChangeEvent(isConnected))
            }
        } else {
            EventBus.getDefault().post(NetworkChangeEvent(isConnected))
        }
    }
}