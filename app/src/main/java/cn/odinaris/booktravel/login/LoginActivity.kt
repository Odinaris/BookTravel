package cn.odinaris.booktravel.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import cn.odinaris.booktravel.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //隐藏状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.act_login)
        initData()
        initView()
    }

    private fun initData() {
        val titles = resources.getStringArray(R.array.loginType)
        val pageAdapter = LoginAdapter(supportFragmentManager,titles.toList())

    }

    private fun initView() {

    }
}