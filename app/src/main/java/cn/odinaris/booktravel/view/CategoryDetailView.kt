package cn.odinaris.booktravel.view

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import cn.odinaris.booktravel.R
import kotlinx.android.synthetic.main.category_item_book.view.*

class CategoryDetailView:CardView{
    var mCategoryName = TextView(context)
    var mFollowNum = TextView(context)
    var mBookNum = TextView(context)

    constructor(context: Context) : super(context){ initView(context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){ initView(context) }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.category_item_book,this,true)
        mCategoryName = tv_category_detail_name
        mFollowNum = tv_followNum
        mBookNum = tv_bookNum
    }
}