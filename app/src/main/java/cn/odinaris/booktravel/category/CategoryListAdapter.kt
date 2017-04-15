package cn.odinaris.booktravel.utils

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import cn.odinaris.booktravel.R
import android.support.v4.content.ContextCompat.startActivity
import cn.odinaris.booktravel.bean.BookCategory
import com.bumptech.glide.Glide

class CategoryListAdapter(var categoryList:ArrayList<BookCategory>, val context:Context)
    : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.name.text = categoryList[position].name
        holder.followNum.text = categoryList[position].followNum.toString() + "人关注"
        holder.bookNum.text = categoryList[position].bookNum.toString() + "本书"
        holder.itemView.setOnClickListener {
            val intent = Intent(context,BookListActivity::class.java)
            intent.putExtra("category",categoryList[position].name)
            startActivity(context,intent,null)
        }
        Glide.with(context).load(categoryList[position].categoriyUrl).into(holder.thumbnail)
    }

    override fun getItemCount(): Int { return categoryList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryListAdapter.ViewHolder {
            return ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_category_detail, parent, false))
    }

    override fun getItemViewType(position: Int): Int { return 1 }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById(R.id.tv_category_detail_name) as TextView
        var followNum = itemView.findViewById(R.id.tv_followNum) as TextView
        var bookNum = itemView.findViewById(R.id.tv_bookNum) as TextView
        var thumbnail = itemView.findViewById(R.id.iv_category_thumbnail) as ImageView
    }
}