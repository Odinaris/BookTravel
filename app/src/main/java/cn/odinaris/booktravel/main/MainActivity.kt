package cn.odinaris.booktravel.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.login.LoginActivity
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.act_main)
        initData()
        initView()
    }

    private fun initData() {

    }

    private fun initView() {
        bnb_navigator
                .addItem(BottomNavigationItem(R.drawable.ic_home,"主页"))
                .addItem(BottomNavigationItem(R.drawable.ic_store,"分类"))
                .addItem(BottomNavigationItem(R.drawable.ic_publish,"发布"))
                .addItem(BottomNavigationItem(R.drawable.ic_manage,"管理"))
                .addItem(BottomNavigationItem(R.drawable.ic_user,"我"))
                .setFirstSelectedPosition(0).initialise()
        test.setOnClickListener { startActivity(Intent(this,LoginActivity::class.java)) }
    }
}
