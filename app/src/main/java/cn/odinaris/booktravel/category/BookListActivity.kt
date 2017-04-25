package cn.odinaris.booktravel.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.category.BookTabAdapter
import kotlinx.android.synthetic.main.book_list.*

class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.book_list)
        val categoryName = intent.getStringExtra("category")
        val titles = resources.getStringArray(R.array.bookType)
        val pageAdapter = BookTabAdapter(supportFragmentManager,categoryName,titles.toList())
        vp_book_list.adapter = pageAdapter
        tl_book_type.setupWithViewPager(vp_book_list)
    }
}
