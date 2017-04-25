package cn.odinaris.booktravel.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.recommend.RecommendAdapter
import cn.odinaris.booktravel.recommend.SearchRrsultsAdapter
import kotlinx.android.synthetic.main.search_main.*
import java.util.regex.Pattern

class SearchActivity : AppCompatActivity() {

    val p = Pattern.compile("[0-9]*")!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.search_main)
        initView()
        initListener()
    }

    private fun initListener() {
        tv_cancel.setOnClickListener { finish() }
        et_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId== EditorInfo.IME_ACTION_SEARCH
                    ||(event!=null&&event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                if(et_search.text.toString() != ""){
                    if(p.matcher(et_search.text.toString()).matches()){
                        if(et_search.text.toString().length==13){
                            //符合ISBN要求，以ISBN搜索书籍
                            val query = BmobQuery<BookInfo>()
                            query.addWhereEqualTo("ISBN",et_search.text.toString())
                            query.findObjects(object : FindListener<BookInfo>(){
                                override fun done(p0: MutableList<BookInfo>?, p1: BmobException?) {
                                    val books = p0!! as ArrayList<BookInfo>
                                    if(books.size==0){
                                        Toast.makeText(applicationContext,"未搜索到您要的书籍!",Toast.LENGTH_SHORT).show()
                                    }else{
                                        rv_books.adapter = SearchRrsultsAdapter(books,applicationContext)
                                        rv_books.layoutManager = LinearLayoutManager(applicationContext)
                                    }
                                }
                            })
                        }else if(et_search.text.toString().length>=8){
                            et_search.error = "请检查ISBN输入是否正确!"
                            Toast.makeText(applicationContext,"请检查ISBN输入是否正确!",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        val query = BmobQuery<BookInfo>()
                        query.addWhereEqualTo("name",et_search.text.toString())
                        query.findObjects(object : FindListener<BookInfo>(){
                            override fun done(p0: MutableList<BookInfo>?, p1: BmobException?) {
                                val books = p0!! as ArrayList<BookInfo>
                                if(books.size==0){
                                    Toast.makeText(applicationContext,"未搜索到您要的书籍!",Toast.LENGTH_SHORT).show()
                                }else{
                                    rv_books.adapter = SearchRrsultsAdapter(books,applicationContext)
                                    rv_books.layoutManager = LinearLayoutManager(applicationContext)
                                }
                            }

                        })
                    }
                }
            }
            true
        }
    }

    fun initView(){
        val drawable = ContextCompat.getDrawable(applicationContext,R.drawable.ic_search)
        drawable.setBounds(0,0,40,40)
        et_search.setCompoundDrawables(drawable,null,null,null)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}
