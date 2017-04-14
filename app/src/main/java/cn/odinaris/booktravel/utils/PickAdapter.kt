package cn.odinaris.booktravel.utils

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

class PickAdapter(var categoryList:ArrayList<BookCategory>, val context:Context, var hasMore:Boolean)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val normalType = 0
    val footType = 1
    var hideTips = false//是否隐藏底部

    private val mHandler = Handler(Looper.getMainLooper()) //获取主线程的Handler

    // 获取条目数量，之所以要加1是因为增加了一条footView
    override fun getItemCount(): Int { return categoryList.size + 1 }

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
            when(categoryList[position].parentCategory)
            {
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
//            holder.follow.setOnClickListener {
//                val userInfo = BmobUser.getCurrentUser() as UserInfo
//                val followList = userInfo.favoriteCategories
//                if(!followList.contains(categoryList[position].name)){
//                    followList.add(categoryList[position].name)
//                    val updateUser = UserInfo()
//                    updateUser.favoriteCategories = followList
//                    holder.follow.visibility = View.GONE
//                    holder.progressbar.visibility = View.VISIBLE
//                    updateUser.update(userInfo.objectId,object:UpdateListener(){
//                        override fun done(e: BmobException?) {
//                            if(e==null){
//                                holder.progressbar.visibility = View.GONE
//                                holder.unfollow.visibility = View.VISIBLE
//                            }else{
//                                Toast.makeText(context,"网络连接错误！"+e.message,Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    })
//                }
//            }
//            holder.unfollow.setOnClickListener {
//                val userInfo = BmobUser.getCurrentUser() as UserInfo
//                val followList = userInfo.favoriteCategories
//                if(!followList.contains(categoryList[position].name)){
//                    followList.remove(categoryList[position].name)
//                    val updateUser = UserInfo()
//                    updateUser.favoriteCategories = followList
//                    holder.unfollow.visibility = View.GONE
//                    holder.progressbar.visibility = View.VISIBLE
//                    updateUser.update(userInfo.objectId,object:UpdateListener(){
//                        override fun done(e: BmobException?) {
//                            if(e==null){
//                                holder.progressbar.visibility = View.GONE
//                                holder.follow.visibility = View.VISIBLE
//                            }else{
//                                Toast.makeText(context,"网络连接错误！"+e.message,Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    })
//                }
//            }
        }else if(holder is FootViewHolder){
            holder.footTips.visibility = View.VISIBLE
            if(hasMore){
                hideTips = false
                if(categoryList.size > 0){
                    holder.footTips.text = "正在加载更多..."
                }
            }else{
                if(categoryList.size > 0){
                    holder.footTips.text = "没有更多数据了"
                    // 然后通过延时加载模拟网络请求的时间，在500ms后执行
                    mHandler.postDelayed({
                        // 隐藏提示条
                        holder.footTips.visibility = View.GONE
                        // 将fadeTips设置true
                        hideTips = true
                        // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                        hasMore = true
                    }, 500)
                }
            }
        }
    }
    override fun getItemViewType(position: Int): Int { return if(position == itemCount - 1) footType else normalType }

    // 暴露接口，下拉刷新时，通过暴露方法将数据源置为空
    fun resetData(){ categoryList = ArrayList() }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    fun updateList(newData: ArrayList<BookCategory>?, hasMore: Boolean) {
        // 在原有的数据之上增加新数据
        if (newData != null) { categoryList.addAll(newData) }
        this.hasMore = hasMore
        notifyDataSetChanged()
    }

    class NormalViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var categoryIcon = itemView.findViewById(R.id.iv_icon_category) as ImageView
        var categoryName = itemView.findViewById(R.id.tv_category_name) as TextView
        var categoryInfo = itemView.findViewById(R.id.tv_category_info) as TextView
        var follow = itemView.findViewById(R.id.tv_follow) as TextView
        var unfollow = itemView.findViewById(R.id.tv_unfollow) as TextView
        var progressbar = itemView.findViewById(R.id.pb_loading) as ProgressBar
    }

    class FootViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var footTips = itemView.findViewById(R.id.tv_foot_tips) as TextView
    }
}