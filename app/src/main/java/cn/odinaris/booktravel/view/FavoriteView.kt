package cn.odinaris.booktravel.view

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import me.odinaris.booktravel.utils.DisScrollLinearLayoutManager

class FavoriteView: LinearLayout{
    var mTextView:TextView = TextView(context)
    var mRecyclerView:RecyclerView = RecyclerView(context)
    var mTextColor = Color.BLACK

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
        LayoutInflater.from(context).inflate(R.layout.item_favorite,this,true)
        mTextView = findViewById(R.id.tv_category) as TextView
        mRecyclerView = findViewById(R.id.rv_book_list) as RecyclerView
        mTextView.setTextColor(mTextColor)

    }

    fun setCategoryName(name:String){ mTextView.text = name }

    fun initRecyclerView(hotList:ArrayList<BookInfo>,
            adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>
            , manager:DisScrollLinearLayoutManager){
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = manager

    }
}