package cn.odinaris.booktravel.login

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.utils.ImageUtils
import kotlinx.android.synthetic.main.login_main.*

class LoginActivity : AppCompatActivity() {

    private var bmp: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //隐藏状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.login_main)
        initData()
        initView()
    }

    private fun initData() {
        val titles = resources.getStringArray(R.array.loginType)
        val pageAdapter = LoginAdapter(supportFragmentManager,titles.toList())
        vp_login_content.adapter = pageAdapter
        tl_login_type.setupWithViewPager(vp_login_content)
    }

    private fun initView() {
        bmp = ImageUtils.readBitMap(R.drawable.ic_book,applicationContext)
        iv_book.setImageBitmap(bmp)
    }

    override fun onStop() {
        super.onStop()
        bmp!!.recycle()
        System.gc()
    }
}