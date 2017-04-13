package cn.odinaris.booktravel.utils

import android.content.Context
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
import kotlinx.android.synthetic.main.item_info_category.view.*

class PickAdapter(var categoryList:ArrayList<BookCategory>, val context:Context, val hasMore:Boolean)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val normalType = 0
    val footType = 1


    override fun getItemCount(): Int {
        // 获取条目数量，之所以要加1是因为增加了一条footView
        return categoryList.size + 1}

    // 自定义方法，获取列表中数据源的最后一个位置，比getItemCount少1，因为不计上footView
    fun getRealLastPosition(): Int{ return categoryList.size }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == normalType){
            return NormalViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_info_category, parent, false))
        }else{
            return FootViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_foot_view, parent, false))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is NormalViewHolder) {
            when(categoryList[position].parentCategory){
                "文艺"->{ Glide.with(context).load(R.drawable.ic_cat_literart).into(holder.categoryIcon) }
                "人文社科"->{ Glide.with(context).load(R.drawable.ic_cat_social).into(holder.categoryIcon) }
                "励志"->{ Glide.with(context).load(R.drawable.ic_cat_motivational).into(holder.categoryIcon) }
                "教育"->{ Glide.with(context).load(R.drawable.ic_cat_education).into(holder.categoryIcon) }
                "生活"->{ Glide.with(context).load(R.drawable.ic_cat_life).into(holder.categoryIcon) }
                "科技"->{ Glide.with(context).load(R.drawable.ic_cat_social).into(holder.categoryIcon) }
                "经济管理"->{ Glide.with(context).load(R.drawable.ic_cat_economic).into(holder.categoryIcon) }
            }
            holder.categoryInfo.text = categoryList[position].followNum.toString() +
                    "人关注" + "·共" + categoryList[position].bookNum.toString() + "本书"
            holder.categoryName.text = categoryList[position].name
            holder.followButton.setOnClickListener {
                val userInfo = BmobUser.getCurrentUser() as UserInfo
                val followList = userInfo.followCategory
                if(!followList.contains(categoryList[position].name)){
                    followList.add(categoryList[position].name)
                    val updateUser = UserInfo()
                    updateUser.followCategory = followList
                    holder.followButton.visibility = View.GONE
                    holder.progressbar.visibility = View.VISIBLE
                    updateUser.update(userInfo.objectId,object:UpdateListener(){
                        override fun done(e: BmobException?) {
                            if(e==null){
                                holder.progressbar.visibility = View.GONE
                                holder.unfollowButton.visibility = View.VISIBLE
                            }else{
                                Toast.makeText(context,"网络连接错误！"+e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
            holder.unfollowButton.setOnClickListener {
                val userInfo = BmobUser.getCurrentUser() as UserInfo
                val followList = userInfo.followCategory
                if(!followList.contains(categoryList[position].name)){
                    followList.remove(categoryList[position].name)
                    val updateUser = UserInfo()
                    updateUser.followCategory = followList
                    holder.unfollowButton.visibility = View.GONE
                    holder.progressbar.visibility = View.VISIBLE
                    updateUser.update(userInfo.objectId,object:UpdateListener(){
                        override fun done(e: BmobException?) {
                            if(e==null){
                                holder.progressbar.visibility = View.GONE
                                holder.followButton.visibility = View.VISIBLE
                            }else{
                                Toast.makeText(context,"网络连接错误！"+e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        }else if(holder is FootViewHolder){
            holder.footTips.visibility = View.VISIBLE
            if(hasMore){

            }
        }

    }
    override fun getItemViewType(position: Int): Int { return if(position == itemCount - 1) footType else normalType }




    class NormalViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var categoryIcon = itemView.findViewById(R.id.iv_icon_category) as ImageView
        var categoryName = itemView.findViewById(R.id.tv_category_name) as TextView
        var categoryInfo = itemView.findViewById(R.id.tv_category_info) as TextView
        var followButton = itemView.findViewById(R.id.btn_follow) as Button
        var unfollowButton = itemView.findViewById(R.id.btn_unfollow) as Button
        var progressbar = itemView.findViewById(R.id.pb_loading) as ProgressBar
    }

    class FootViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var footTips = itemView.findViewById(R.id.tv_foot_tips) as TextView
    }
}