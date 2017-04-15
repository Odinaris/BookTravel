package cn.odinaris.booktravel.home

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.book_detail.BookDetailActivity
import com.bumptech.glide.Glide


class HotBooksAdapter(var bookList:ArrayList<BookInfo>, val context: Context)
    : RecyclerView.Adapter<HotBooksAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: HotBooksAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(bookList[position].img1).into(holder.cover)
        holder.category.text = bookList[position].category
        holder.name.text = bookList[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("objectId",bookList[position].objectId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int { return bookList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HotBooksAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_hot_book_list,parent, false))

    }

    override fun getItemViewType(position: Int): Int { return position }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var cover = itemView.findViewById(R.id.iv_hot_cover) as ImageView
        var name = itemView.findViewById(R.id.tv_book_name) as TextView
        var category = itemView.findViewById(R.id.tv_book_category) as TextView
    }

}