package cn.odinaris.booktravel.home

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
import kotlinx.android.synthetic.main.frag_home.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_home,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
    }

    private fun initData() {
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
                    //Toast.makeText(context,categoryList.toString()+"种分类",Toast.LENGTH_SHORT).show()
                    hotQuery.addWhereContainedIn("category", categoryList)
                    hotQuery.setLimit(10)
                    hotQuery.findObjects(object : FindListener<BookInfo>(){
                        override fun done(books: MutableList<BookInfo>?, e1: BmobException?) {
                            if(e1==null){
                                Toast.makeText(context,books!!.size.toString(),Toast.LENGTH_SHORT).show()
                                val bookList = books as ArrayList<BookInfo>
                                rv_hot_list.adapter = HotBooksAdapter(bookList,context)
                                rv_hot_list.layoutManager = GridLayoutManager(context,2)
                                tv_loading.visibility = View.GONE
                                rv_hot_list.visibility = View.VISIBLE
                            }else{
                                Toast.makeText(context,e1.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        })
    }

}
