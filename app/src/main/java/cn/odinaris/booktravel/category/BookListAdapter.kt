package cn.odinaris.booktravel.category

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bookinfo.BookDetailActivity
import com.bumptech.glide.Glide



class BookListAdapter(var flag:Int, var bookList:ArrayList<BookInfo>, val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder!!.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("objectId",bookList[position].objectId)
            context.startActivity(intent)
        }
        if(holder is SaleViewHolder){
            holder.category.text = bookList[position].category
            holder.tipsCategory.text = "分类:"
            holder.name.text = bookList[position].name
            holder.author.text = "作者:" + bookList[position].author
            holder.press.text = "出版社:" + bookList[position].press
            holder.newPrice.text = bookList[position].newPrice.toString()
            holder.price.text = "￥" + bookList[position].price.toString()
            holder.newPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            if(bookList[position].oldDegree ==10f) {
                holder.oldDegreeTips.visibility = View.GONE
                holder.oldDegree.text = "全新"
                holder.oldDegree.textSize = 24f
            }else{
                holder.oldDegree.text = bookList[position].oldDegree.toString()
            }
            Glide.with(context).load(bookList[position].img1).into(holder.thumbnail)
        }
        else if(holder is CrossViewHolder){
            holder.category.text = bookList[position].category
            holder.tipsCategory.text = "分类:"
            holder.name.text = bookList[position].name
            holder.author.text = "作者:" + bookList[position].author
            holder.press.text = "出版社:" + bookList[position].press
            holder.tripNum.text = bookList[position].tripNum.toString()
            Glide.with(context).load(bookList[position].img1).into(holder.thumbnail)
        }
    }

    override fun getItemCount(): Int { return bookList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0->{ return SaleViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_sale, parent, false)) }
            1->{ return CrossViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_cross, parent, false)) }
            else -> { return SaleViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_sale, parent, false))
            }
        }
    }

    override fun getItemViewType(position: Int): Int { return flag }

    class SaleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById(R.id.tv_sale_name) as TextView
        var author = itemView.findViewById(R.id.tv_author) as TextView
        var press = itemView.findViewById(R.id.tv_press) as TextView
        var thumbnail = itemView.findViewById(R.id.iv_thumbnail_book) as ImageView
        var price = itemView.findViewById(R.id.tv_price) as TextView
        var newPrice = itemView.findViewById(R.id.tv_new_price) as TextView
        var oldDegree = itemView.findViewById(R.id.tv_old_degree) as TextView
        var oldDegreeTips = itemView.findViewById(R.id.tv_tips_old_degree) as TextView
        var tipsCategory = itemView.findViewById(R.id.tv_tips_category) as TextView
        var category = itemView.findViewById(R.id.tv_category) as TextView
    }

    class CrossViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById(R.id.tv_cross_name) as TextView
        var author = itemView.findViewById(R.id.tv_author) as TextView
        var press = itemView.findViewById(R.id.tv_press) as TextView
        var tripNum = itemView.findViewById(R.id.tv_tripNum) as TextView
        var thumbnail = itemView.findViewById(R.id.iv_thumbnail_book) as ImageView
        var tipsCategory = itemView.findViewById(R.id.tv_tips_category) as TextView
        var category = itemView.findViewById(R.id.tv_category) as TextView
    }
}