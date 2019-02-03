package com.hzw.wanapp.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class MultipleStatusView : RelativeLayout {

    companion object {
        const val TAG = "MultipleStatusView"
        val DEFAULT_LAYOUT_PARAMS = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )

        const val STATUS_CONTENT = 0x00
        const val STATUS_LOADING = 0x01
        const val STATUS_EMPTY = 0x02
        const val STATUS_ERROR = 0x03
        const val STATUS_NO_NETWORK = 0x04

        const val NULL_RESOURCE_ID = -1
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }
}