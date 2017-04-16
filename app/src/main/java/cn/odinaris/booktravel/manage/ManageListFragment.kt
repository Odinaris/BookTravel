package cn.odinaris.booktravel.manage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bean.UserInfo
import cn.odinaris.booktravel.utils.ManageListAdapter
import kotlinx.android.synthetic.main.frag_manage_list.*

class ManageListFragment(val flag:Int) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_manage_list,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
    }

    override fun onResume() {
        super.onResume()
        initData()//网络加载、数据请求操作
    }

    private fun initData() {
        val innerQuery = BmobQuery<UserInfo>()
        val userQuery = BmobQuery<BookInfo>()
        val flagQuery = BmobQuery<BookInfo>()
        val queryList = ArrayList<BmobQuery<BookInfo>>()
        val query = BmobQuery<BookInfo>()
        innerQuery.addWhereEqualTo("objectId", BmobUser.getCurrentUser().objectId)
        userQuery.addWhereMatchesQuery("belongUser","_User",innerQuery)
        flagQuery.addWhereEqualTo("flag",flag)
        queryList.add(userQuery)
        queryList.add(flagQuery)
        query.and(queryList)
        query.findObjects(object: FindListener<BookInfo>(){
            override fun done(bookList: MutableList<BookInfo>?, e: BmobException?) {
                if(e == null&&bookList!=null){
                    val books = bookList as ArrayList<BookInfo>
                    rv_manage_list.adapter = ManageListAdapter(flag,books,context)
                    rv_manage_list.layoutManager = LinearLayoutManager(context)
                    ll_loading.visibility = View.GONE
                    rv_manage_list.visibility = View.VISIBLE
                }
            }
        })
    }
}