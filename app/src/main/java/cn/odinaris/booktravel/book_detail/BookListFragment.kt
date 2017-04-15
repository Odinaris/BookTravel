package cn.odinaris.booktravel.book_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import kotlinx.android.synthetic.main.frag_book_list.*

class BookListFragment(var flag:Int, val category:String) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_book_list,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
    }

    private fun initView() {

    }

    private fun initData() {

        val flagQuery = BmobQuery<BookInfo>()
        val categoryQuery = BmobQuery<BookInfo>()
        val queryList = ArrayList<BmobQuery<BookInfo>>()
        val query = BmobQuery<BookInfo>()
        flagQuery.addWhereEqualTo("flag",flag)
        categoryQuery.addWhereEqualTo("category",category)
        queryList.add(flagQuery)
        queryList.add(categoryQuery)
        query.and(queryList)
        query.findObjects(object: FindListener<BookInfo>(){
            override fun done(bookList: MutableList<BookInfo>?, e: BmobException?) {
                if(e == null&&bookList!=null){
                    Toast.makeText(context,category+"分类共有"+bookList.size.toString()+"本书",Toast.LENGTH_SHORT).show()
                    val books = bookList as ArrayList<BookInfo>
                    rv_book_list.adapter = BookListAdapter(flag,books,context)
                    rv_book_list.layoutManager = LinearLayoutManager(context)
                    tv_loading.visibility = View.GONE
                    rv_book_list.visibility = View.VISIBLE
                }
            }
        })

    }
}