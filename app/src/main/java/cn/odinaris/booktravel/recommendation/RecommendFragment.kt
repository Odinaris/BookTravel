package cn.odinaris.booktravel.recommendation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bean.UserInfo
import kotlinx.android.synthetic.main.frag_recommend.*
import kotlin.collections.ArrayList

class RecommendFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_recommend,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
    }

    private fun initData() {
        //建立内部查询
        val innerQuery = BmobQuery<UserInfo>()
        innerQuery.addWhereEqualTo("objectId",BmobUser.getCurrentUser().objectId)
        val followedQuery = BmobQuery<BookCategory>()
        followedQuery.addWhereMatchesQuery("followedUsers","_User",innerQuery)
        followedQuery.findObjects(object : FindListener<BookCategory>(){
            override fun done(categories: MutableList<BookCategory>?, e: BmobException?) {
                if(e == null && categories != null){
                    val hotQuery = BmobQuery<BookInfo>()
                    val categoryList = ArrayList<String>()
                    categories.mapTo(categoryList) { it.name }
                    hotQuery.addWhereContainedIn("category", categoryList)
                    hotQuery.setLimit(20)
                    hotQuery.findObjects(object : FindListener<BookInfo>(){
                        override fun done(books: MutableList<BookInfo>?, e1: BmobException?) {
                            if(e1==null){
                                Toast.makeText(
                                        context,"为您最新推荐"+books!!.size.toString()+"本书",Toast.LENGTH_SHORT)
                                        .show()
                                val bookList = books as ArrayList<BookInfo>
                                rv_hot_list.adapter = RecommendAdapter(bookList,context)
                                rv_hot_list.layoutManager = GridLayoutManager(context,2)
                                pb_loading.visibility = View.GONE
                                rv_hot_list.visibility = View.VISIBLE
                            }else{ Toast.makeText(context,e1.message,Toast.LENGTH_SHORT).show() }
                        }
                    })
                }
            }
        })
    }
}
