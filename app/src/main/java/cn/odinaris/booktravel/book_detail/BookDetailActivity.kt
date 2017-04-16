package cn.odinaris.booktravel.book_detail

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobRealTimeData
import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobRelation
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bean.UserInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.act_book_detail.*


class BookDetailActivity : AppCompatActivity() {

    var objectId = ""
    var user = UserInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.act_book_detail)
        objectId = intent.getStringExtra("objectId")
        user = BmobUser.getCurrentUser<UserInfo>(UserInfo::class.java)
        initView()
        initData()
        initClickListener()
    }

    private fun initClickListener() {
        tv_follow.setOnClickListener{
            when(tv_follow.text){
                "已关注" -> {
                    val query = BmobQuery<BookInfo>()
                    query.getObject(objectId,object:QueryListener<BookInfo>(){
                        override fun done(p0: BookInfo, p1: BmobException?) {
                            val book = p0
                            book.objectId = objectId
                            book.followedUsers.remove(user)
                            book.update(object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    tv_follow.text = "关注"
                                    tv_follow.setTextColor(Color.BLACK)
                                }
                            })
                        }
                    })
                }
                "关注" -> {
                    val query = BmobQuery<BookInfo>()
                    query.getObject(objectId,object:QueryListener<BookInfo>(){
                        override fun done(p0: BookInfo, p1: BmobException?) {
                            val book = p0
                            book.objectId = objectId
                            book.followedUsers.add(user)
                            book.update(object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    tv_follow.text = "已关注"
                                    tv_follow.setTextColor(Color.WHITE)
                                }
                            })
                        }
                    })
                }
            }
        }
        tv_get.setOnClickListener {
            val query = BmobQuery<BookInfo>()
            query.include("belongUser")
            query.getObject(objectId,object:QueryListener<BookInfo>(){
                override fun done(book: BookInfo?, e: BmobException?) {
                    if(e == null && book != null){
                        if(book.belongUser!=null){
                            val user:UserInfo = book.belongUser!!
                            if(user.username!=null){
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
        nsv_container.visibility = View.GONE
        ll_submit.visibility = View.GONE
    }

    private fun initData() {
        val query = BmobQuery<BookInfo>()
        query.getObject(objectId,object: QueryListener<BookInfo>(){
            override fun done(book: BookInfo, e: BmobException?) {
                if(e == null){
                    tv_detail_author.text = "作者:" + book.author
                    tv_detail_category.text = book.category
                    tv_detail_isbn.text = "ISBN:" + book.ISBN
                    tv_detail_name.text = book.name
                    tv_detail_title.text = book.name
                    tv_detail_press.text = "出版社:" + book.press
                    tv_detail_price.text = "￥"+book.price.toString()
                    tv_detail_newPrice.text = "￥"+book.newPrice.toString()
                    tv_detail_old_degree.text = book.oldDegree.toString()
                    val tripNum = book.tripNum
                    tv_tripNum.text = "本书目前共有$tripNum 段旅程"
                    if(book.flag!=0 && book.flag!=3){
                        cv_saleInfo.visibility = View.GONE
                    }else{
                        cv_crossInfo.visibility = View.GONE
                    }
                    Glide.with(applicationContext).load(book.cover).into(iv_detail_cover)
                    Glide.with(applicationContext).load(book.img2).into(iv_img2)
                    Glide.with(applicationContext).load(book.img3).into(iv_img3)
                    ll_loadingView.visibility = View.GONE
                    iv_detail_cover.visibility = View.VISIBLE
                    nsv_container.visibility = View.VISIBLE
                    ll_submit.visibility = View.VISIBLE
                }
            }

        })
    }
}
