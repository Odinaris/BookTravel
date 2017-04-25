package cn.odinaris.booktravel.bookinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.book_detail.*

class BookDetailFragment(var book: BookInfo) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.book_detail,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tv_name.text = book.name
        tv_author.text = "作者:" + book.author
        if(book.author_intro=="") cv_author_intro.visibility = View.GONE
        tv_author_intro.text = book.author_intro
        tv_isbn_13.text = "ISBN:" + book.ISBN
        tv_new_price.text = "定价:" + book.newPrice.toString()
        tv_press.text = "出版社:" + book.press
        tv_pages.text = "页数:" + book.pages
        if(book.summary == "") cv_summary.visibility = View.GONE
        tv_summary.text = book.summary
        Glide.with(context).load(book.cover).into(iv_cover)
    }
}