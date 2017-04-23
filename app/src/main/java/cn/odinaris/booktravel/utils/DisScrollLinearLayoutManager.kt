package me.odinaris.booktravel.utils

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

class DisScrollLinearLayoutManager(context: Context) : LinearLayoutManager(context) {
    var isScrollEnabled = true
    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}