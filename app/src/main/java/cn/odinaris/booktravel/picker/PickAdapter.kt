package cn.odinaris.booktravel.picker

import android.content.Context
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
import cn.bmob.v3.datatype.BmobRelation

class PickAdapter(var categoryList:ArrayList<BookCategory>, val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var followedList = ArrayList<BookCategory>()

    override fun getItemCount(): Int { return categoryList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.utils_pick_category, parent, false))

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            Glide.with(context).load(categoryList[position].categoriyUrl).into(holder.categoryIcon)
            holder.categoryInfo.text = categoryList[position].followNum.toString() +
                    "人关注" + "·共" + categoryList[position].bookNum.toString() + "本书"
            holder.categoryName.text = categoryList[position].name
            for(category: BookCategory in followedList){
                if(category.name == categoryList[position].name){
                    holder.follow.visibility = View.GONE
                    holder.unfollow.visibility = View.VISIBLE
                }
            }
            holder.follow.setOnClickListener {
                holder.follow.visibility = View.GONE
                holder.progressbar.visibility = View.VISIBLE
                val user = BmobUser.getCurrentUser<UserInfo>(UserInfo::class.java)
                val relation = BmobRelation()
                relation.add(user)
                categoryList[position].followedUsers = relation
                categoryList[position].update(object : UpdateListener() {
                    override fun done(e: BmobException?) {
                        if (e == null) {
                            holder.progressbar.visibility = View.GONE
                            holder.unfollow.visibility = View.VISIBLE
                        } else {
                            holder.progressbar.visibility = View.GONE
                            holder.follow.visibility = View.VISIBLE
                        }
                    }
                })
            }
            holder.unfollow.setOnClickListener {
                holder.unfollow.visibility = View.GONE
                holder.progressbar.visibility = View.VISIBLE
                val user = BmobUser.getCurrentUser<UserInfo>(UserInfo::class.java)
                val relation = BmobRelation()
                relation.remove(user)
                categoryList[position].followedUsers = relation
                categoryList[position].update(object : UpdateListener() {
                    override fun done(e: BmobException?) {
                        if (e == null) {
                            holder.progressbar.visibility = View.GONE
                            holder.follow.visibility = View.VISIBLE
                        } else {
                            holder.progressbar.visibility = View.GONE
                            holder.unfollow.visibility = View.VISIBLE
                        }
                    }
                })
            }
        }
    }

    fun setFollowedCategories(category:ArrayList<BookCategory>){ followedList = category }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var categoryIcon = itemView.findViewById(R.id.iv_icon_category) as ImageView
        var categoryName = itemView.findViewById(R.id.tv_category_name) as TextView
        var categoryInfo = itemView.findViewById(R.id.tv_category_info) as TextView
        var follow = itemView.findViewById(R.id.tv_follow) as TextView
        var unfollow = itemView.findViewById(R.id.tv_unfollow) as TextView
        var progressbar = itemView.findViewById(R.id.pb_loading) as ProgressBar
    }
}