package cn.odinaris.booktravel.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import me.odinaris.booktravel.utils.DisScrollLinearLayoutManager

class SettingView: LinearLayout{
    var mLeftContent = TextView(context)
    var mRightContent = TextView(context)
    var mLeftIcon = ImageView(context)
    var mRightIcon = ImageView(context)
    var mLeftContentColor = Color.BLACK
    var mRightContentColor = Color.BLACK

    constructor(context: Context) : super(context){
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView(context)
    }

    private fun  initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.item_setting,this,true)
        mLeftIcon = findViewById(R.id.iv_icon_left) as ImageView
        mRightIcon = findViewById(R.id.iv_icon_right) as ImageView
        mLeftContent = findViewById(R.id.tv_content_left) as TextView
        mRightContent = findViewById(R.id.tv_content_right) as TextView
        mLeftContent.setTextColor(mLeftContentColor)
        mRightContent.setTextColor(mRightContentColor)

    }

}