package cn.odinaris.booktravel.book_detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.act_book_detail.*


class BookDetailActivity : AppCompatActivity() {

    var objectId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.act_book_detail)
        initView()
        initData()
    }

    private fun initView() {
        nsv_container.visibility = View.GONE
        ll_submit.visibility = View.GONE
    }

    private fun initData() {

        objectId = intent.getStringExtra("objectId")
        val query = BmobQuery<BookInfo>()
        query.getObject(objectId,object: QueryListener<BookInfo>(){
            override fun done(book: BookInfo, e: BmobException?) {
                if(e == null){
                    tv_book_author.text = "作者:" + book.author
                    tv_book_category.text = book.category
                    tv_book_isbn.text = "ISBN:" + book.ISBN
                    tv_book_name.text = book.name
                    tv_book_title.text = book.name
                    tv_book_press.text = "出版社:" + book.press
                    tv_book_price.text = "￥"+book.price.toString()
                    tv_book_newPrice.text = "￥"+book.newPrice.toString()
                    val tripNum = book.tripNum
                    tv_tripNum.text = "本书目前共有$tripNum 段旅程"
                    if(book.flag!=0||book.flag!=3){
                        cv_saleInfo.visibility = View.GONE
                    }else{
                        cv_crossInfo.visibility = View.GONE
                    }
                    Glide.with(applicationContext).load(book.img1).into(iv_book_cover)
                    Glide.with(applicationContext).load(book.img2).into(iv_img2)
                    Glide.with(applicationContext).load(book.img3).into(iv_img3)
                    nsv_container.visibility = View.VISIBLE
                    ll_submit.visibility = View.VISIBLE
                }
            }

        })
    }
}
