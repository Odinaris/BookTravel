package cn.odinaris.booktravel.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import cn.odinaris.booktravel.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
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