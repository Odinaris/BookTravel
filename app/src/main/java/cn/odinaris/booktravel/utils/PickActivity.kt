package cn.odinaris.booktravel.utils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.OnClickListener
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener

import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory

import kotlinx.android.synthetic.main.act_pick.*

class PickActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_pick)
        initView()
        initData()
    }

    private fun initData() {
        val query = BmobQuery<BookCategory>()
        query.addWhereNotEqualTo("name","")
        query.findObjects(object : FindListener<BookCategory>(){
            override fun done(category: MutableList<BookCategory>?, e: BmobException?) {
                if(category!=null&&e==null){
                    val categoryList = category as ArrayList<BookCategory>
                    rv_categories.adapter = PickAdapter(categoryList,applicationContext,false)
                    rv_categories.layoutManager = LinearLayoutManager(this@PickActivity)
                }
            }

        })
    }

    private fun initView() {
        initRefreshLayout()
    }

    //初始化SwipeRefreshLayout
    private fun initRefreshLayout() {
        srl_categories.setColorSchemeColors(
                android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light)
        srl_categories.setOnRefreshListener { onRefresh() }
    }

    override fun onRefresh() {

    }
}
