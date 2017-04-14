package cn.odinaris.booktravel.view

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import cn.odinaris.booktravel.R

class CategoryDetailView:CardView{
    var mTextView: TextView = TextView(context)
    var mRecyclerView: RecyclerView = RecyclerView(context)
    var mTextColor = Color.BLACK

    constructor(context: Context) : super(context){ initView(context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){ initView(context) }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.item_category_detail,this,true)
    }
}