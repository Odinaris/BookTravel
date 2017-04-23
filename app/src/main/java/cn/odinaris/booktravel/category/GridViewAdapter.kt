package cn.odinaris.booktravel.category

import android.content.Context
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import cn.odinaris.booktravel.R
import android.widget.TextView

class GridViewAdapter(var context: Context,var categoryList: List<String>) : BaseAdapter(){

    override fun getCount(): Int { return categoryList.size }

    override fun getItem(position: Int): Any { return categoryList[position] }

    override fun getItemId(position: Int): Long { return position.toLong() }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflate(context, R.layout.category_item_book, null)
        val categoryName = view.findViewById(R.id.tv_category_detail_name) as TextView
        categoryName.text = categoryList[position]
        categoryName.text =view.height.toString()
        return view
    }
}