package cn.odinaris.booktravel.utils

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import cn.odinaris.booktravel.bean.UserInfo
import com.bumptech.glide.Glide
import android.os.Looper
import android.support.v4.content.ContextCompat.startActivity

class CategoryListAdapter(var categoryList:List<String>, val context:Context)
    : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.name.text = categoryList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context,BookListActivity::class.java)
            intent.putExtra("category",categoryList[position])
            startActivity(context,intent,null)
        }
    }

    override fun getItemCount(): Int { return categoryList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryListAdapter.ViewHolder {
            return ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_category_detail, parent, false))
    }

    override fun getItemViewType(position: Int): Int { return 1 }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
       var name = itemView.findViewById(R.id.tv_category_detail_name) as TextView
    }
}