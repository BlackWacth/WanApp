package com.hzw.wanapp.rx

import com.hzw.wanapp.rx.scheduler.IOMainScheduler

object SchedulerUtils {

    fun <T> ioToMain(): IOMainScheduler<T> {
        return IOMainScheduler()
    }

}