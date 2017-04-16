package cn.odinaris.booktravel.recommendation

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


class RecommendAdapter(var bookList:ArrayList<BookInfo>, val context: Context)
    : RecyclerView.Adapter<RecommendAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: RecommendAdapter.ViewHolder, position: Int) {
        if (bookList[position].cover==""){
            Glide.with(context).load(bookList[position].img1).into(holder.cover)
        }else{
            Glide.with(context).load(bookList[position].cover).into(holder.cover)
        }
        holder.category.text = bookList[position].category
        holder.name.text = bookList[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("objectId",bookList[position].objectId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int { return bookList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecommendAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recommend_books,parent, false)
        return ViewHolder(view)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var cover = itemView.findViewById(R.id.iv_recommend_cover) as ImageView
        var name = itemView.findViewById(R.id.tv_recommend_name) as TextView
        var category = itemView.findViewById(R.id.tv_recommend_category) as TextView
    }
}