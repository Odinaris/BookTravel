package cn.odinaris.booktravel.utils

import android.content.Context
import android.support.v7.widget.GridLayoutManager

/**
 * Created by Odinaris on 2017/4/25.
 */
class DisScrollGridLayoutManager(context: Context,int:Int) : GridLayoutManager(context,int) {
    var isScrollEnabled = true
    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}