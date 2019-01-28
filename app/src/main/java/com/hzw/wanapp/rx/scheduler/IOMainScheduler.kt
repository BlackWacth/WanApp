package com.hzw.wanapp.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IOMainScheduler<T>: BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())