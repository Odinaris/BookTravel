package cn.odinaris.booktravel.book_detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

import cn.odinaris.booktravel.R

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.act_book_detail)
    }
}
