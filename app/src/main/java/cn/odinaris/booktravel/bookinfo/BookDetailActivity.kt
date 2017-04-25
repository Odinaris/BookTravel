package cn.odinaris.booktravel.bookinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bean.UserInfo
import cn.odinaris.booktravel.category.BookInfoTabAdapter
import kotlinx.android.synthetic.main.book_main.*
import kotlinx.android.synthetic.main.book_submit.*


class BookDetailActivity : AppCompatActivity() {

    var objectId = ""
    var user = UserInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.book_main)
        objectId = intent.getStringExtra("objectId")
        user = BmobUser.getCurrentUser<UserInfo>(UserInfo::class.java)
        initView()
        initData()
        initClickListener()
    }

    private fun initClickListener() {
//        tv_follow.setOnClickListener{
//            when(tv_follow.text){
//                "已关注" -> {
//                    val query = BmobQuery<BookInfo>()
//                    query.getObject(objectId,object:QueryListener<BookInfo>(){
//                        override fun done(p0: BookInfo, p1: BmobException?) {
//                            val book = p0
//                            book.objectId = objectId
//                            book.followedUsers.remove(user)
//                            book.update(object:UpdateListener(){
//                                override fun done(p0: BmobException?) {
//                                    tv_follow.text = "关注"
//                                    tv_follow.setTextColor(Color.BLACK)
//                                }
//                            })
//                        }
//                    })
//                }
//                "关注" -> {
//                    val query = BmobQuery<BookInfo>()
//                    query.getObject(objectId,object:QueryListener<BookInfo>(){
//                        override fun done(p0: BookInfo, p1: BmobException?) {
//                            val book = p0
//                            book.objectId = objectId
//                            book.followedUsers.add(user)
//                            book.update(object:UpdateListener(){
//                                override fun done(p0: BmobException?) {
//                                    tv_follow.text = "已关注"
//                                    tv_follow.setTextColor(Color.WHITE)
//                                }
//                            })
//                        }
//                    })
//                }
//            }
//        }
        tv_want.setOnClickListener {
            val query = BmobQuery<BookInfo>()
            query.include("belongUser")
            query.getObject(objectId,object:QueryListener<BookInfo>(){
                override fun done(book: BookInfo?, e: BmobException?) {
                    if(e == null && book != null){
                        if(book.belongUser!=null){
                            val user:UserInfo = book.belongUser!!
                            if(user.mobilePhoneNumber!=""){
                                Toast.makeText(applicationContext,user.mobilePhoneNumber,Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(applicationContext,"当前用户未留联系方式!",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            })
        }
    }

    private fun initView() {
    }

    private fun initData() {
        val query = BmobQuery<BookInfo>()
        query.getObject(objectId,object: QueryListener<BookInfo>(){
            override fun done(book: BookInfo, e: BmobException?) {
                if(e == null){
                    if(book.flag!=0){
                        tv_price.visibility = View.GONE
                        ll_break.visibility = View.GONE
                        tv_want.text = "借阅"
                    }else{
                        tv_price.text = "价格:" + book.price.toString() + "元"
                        tv_want.text = "购买"
                    }
                    val titles = ArrayList<String>()
                    titles.add("详情")
                    titles.add("实物图")
                    //漂流书籍添加漂流历史记录
                    //if(book.flag == 1) titles.add("漂流信息")
                    val bookInfoPageAdapter = BookInfoTabAdapter(supportFragmentManager, book,titles)
                    vp_info.adapter = bookInfoPageAdapter
                    tl_info.setupWithViewPager(vp_info)
                }
            }
        })
    }
}