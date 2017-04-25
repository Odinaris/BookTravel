package cn.odinaris.booktravel.utils

import android.content.Context
import android.graphics.Paint
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import com.bumptech.glide.Glide



class ManageListAdapter(var flag:Int, var bookList:ArrayList<BookInfo>, val context:Context)
    : RecyclerView.Adapter< RecyclerView.ViewHolder>(){

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val manageBook = bookList[position]
        val cover = if(manageBook.cover!="") manageBook.cover else manageBook.img1
        holder!!.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            when(flag){
                0 -> { dialog.setMessage("取消发售?")
                            .setNegativeButton("否",null)
                            .setPositiveButton("是", { dialog, which ->
                                manageBook.flag = 3
                                manageBook.update(bookList[position].objectId,object:UpdateListener(){
                                    override fun done(p0: BmobException?) {
                                        Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show()
                                        bookList.removeAt(position)
                                        notifyItemRemoved(position)
                                        notifyItemRangeChanged(0,bookList.size-position)
                                    }
                                })
                            }).show()
                }
                1 -> { dialog.setMessage("暂停漂流?")
                            .setNegativeButton("否",null)
                            .setPositiveButton("是", { dialog, which ->
                                manageBook.flag = 2
                                manageBook.update(bookList[position].objectId,object:UpdateListener(){
                                    override fun done(p0: BmobException?) {
                                        Toast.makeText(context,"重新发售成功",Toast.LENGTH_SHORT).show()
                                        bookList.removeAt(position)
                                        notifyItemRemoved(position)
                                        notifyItemRangeChanged(0,bookList.size-position)
                                    }
                                })
                            }).show()}
                2 -> { dialog.setMessage("开始漂流?")
                        .setNegativeButton("否",null)
                        .setPositiveButton("是", { dialog, which ->
                            manageBook.flag = 1
                            manageBook.update(bookList[position].objectId,object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    Toast.makeText(context,"重新发售成功",Toast.LENGTH_SHORT).show()
                                    bookList.removeAt(position)
                                    notifyItemRemoved(position)
                                    notifyItemRangeChanged(0,bookList.size-position)
                                }
                            })
                        }).show()}
                3 -> { dialog.setMessage("重新发售?")
                        .setNegativeButton("否",null)
                        .setPositiveButton("是", { dialog, which ->
                            manageBook.flag = 0
                            manageBook.update(bookList[position].objectId,object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    Toast.makeText(context,"重新发售成功",Toast.LENGTH_SHORT).show()
                                    bookList.removeAt(position)
                                    notifyItemRemoved(position)
                                    notifyItemRangeChanged(0,bookList.size-position)
                                }
                            })
                        }).show()}
            }
        }
        if(holder is SaleViewHolder){
            holder.categoryTips.text = "分类"
            holder.category.text = manageBook.category
            holder.name.text = manageBook.name
            holder.author.text = "作者:" + manageBook.author
            holder.press.text = "出版社:" + manageBook.press
            holder.newPrice.text = manageBook.newPrice.toString()
            holder.price.text = "￥" + manageBook.price.toString()
            holder.newPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            if(bookList[position].oldDegree==10f) {
                holder.oldDegreeTips.visibility = View.GONE
                holder.oldDegree.text = "全新"
                holder.oldDegree.textSize = 24f
            }else{
                holder.oldDegree.text = manageBook.oldDegree.toString()
            }
            Glide.with(context).load(cover).into(holder.thumbnail)
        }
        else if(holder is CrossViewHolder){
            holder.categoryTips.text = "分类"
            holder.category.text = manageBook.category
            holder.name.text = manageBook.name
            holder.author.text = "作者:" + manageBook.author
            holder.press.text = "出版社:" + manageBook.press
            holder.tripNum.text = manageBook.tripNum.toString()
            Glide.with(context).load(cover).into(holder.thumbnail)
        }
    }

    override fun getItemCount(): Int { return bookList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):  RecyclerView.ViewHolder {
        when(viewType){
            0->{ return SaleViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_sale, parent, false)) }
            1->{ return CrossViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_cross, parent, false)) }
            2->{ return CrossViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_cross, parent, false)) }
            3->{ return SaleViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.book_item_sale, parent, false)) }
            else->{
                return SaleViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item_sale, parent, false))
            }
        }
    }

    override fun getItemViewType(position: Int): Int { return flag }

    class SaleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var categoryTips = itemView.findViewById(R.id.tv_tips_category) as TextView
        var name = itemView.findViewById(R.id.tv_sale_name) as TextView
        var author = itemView.findViewById(R.id.tv_author) as TextView
        var press = itemView.findViewById(R.id.tv_press) as TextView
        var thumbnail = itemView.findViewById(R.id.iv_thumbnail_book) as ImageView
        var price = itemView.findViewById(R.id.tv_price) as TextView
        var newPrice = itemView.findViewById(R.id.tv_new_price) as TextView
        var oldDegree = itemView.findViewById(R.id.tv_old_degree) as TextView
        var oldDegreeTips = itemView.findViewById(R.id.tv_tips_old_degree) as TextView
        var category = itemView.findViewById(R.id.tv_category) as TextView
    }

    class CrossViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var categoryTips = itemView.findViewById(R.id.tv_tips_category) as TextView
        var name = itemView.findViewById(R.id.tv_cross_name) as TextView
        var author = itemView.findViewById(R.id.tv_author) as TextView
        var press = itemView.findViewById(R.id.tv_press) as TextView
        var tripNum = itemView.findViewById(R.id.tv_tripNum) as TextView
        var thumbnail = itemView.findViewById(R.id.iv_thumbnail_book) as ImageView
        var category = itemView.findViewById(R.id.tv_category) as TextView
    }
}