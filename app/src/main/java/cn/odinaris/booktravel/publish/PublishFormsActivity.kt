package cn.odinaris.booktravel.publish

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.DoubanInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.publish_bookinfo.*

class PublishFormsActivity : AppCompatActivity() {

    private var bookInfo = DoubanInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.publish_bookinfo)
        initData()
        initView()
        initClickListener()
    }
    private fun initData() { bookInfo = intent.getSerializableExtra("BookInfo") as DoubanInfo }
    private fun initView() {
        tv_title.text = bookInfo.title
        tv_name.text = bookInfo.title
        tv_translator.text = "译者:"+ bookInfo.translator.toString()
        tv_author.text = "作者:"+ bookInfo.author.toString()
        tv_publisher.text = "出版社:"+ bookInfo.publisher
        tv_author_intro.text = bookInfo.author_intro
        tv_binding.text = "装帧:"+ bookInfo.binding
        tv_isbn_13.text = "ISBN:"+ bookInfo.isbn13
        tv_pages.text = "页数:"+ bookInfo.pages
        tv_price.text = "定价:"+ bookInfo.price
        tv_publish_date.text = "出版日期:"+ bookInfo.pubdate
        tv_summary.text = bookInfo.summary
        Glide.with(applicationContext).load(bookInfo.image).into(iv_publish_image)
    }
    private fun initClickListener() {
        tv_sale.setOnClickListener {
            val intent = Intent(this,PublishActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("BookInfo",bookInfo)
            bundle.putInt("type",0)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }
        tv_cross.setOnClickListener {
            val intent = Intent(this,PublishActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("BookInfo",bookInfo)
            bundle.putInt("type",1)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }
    }
}
