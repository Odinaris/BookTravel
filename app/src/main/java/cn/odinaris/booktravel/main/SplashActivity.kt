package cn.odinaris.booktravel.main

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import cn.bmob.v3.Bmob
import cn.odinaris.booktravel.MainActivity
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.utils.ImageUtils
import kotlinx.android.synthetic.main.act_splash.*

class SplashActivity : Activity() {

    private var bmp: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)
        Bmob.initialize(this, "6590c04360b490a8625cebf8826457b3")
        initImage()
    }

    private fun initImage() {
        bmp = ImageUtils.readBitMap(R.drawable.banner,applicationContext)
        iv_start.setImageBitmap(bmp)
        //设置缩放动画
        val animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.anim_start)
        animation.setAnimationListener(object : Animation.AnimationListener {
            //可以在这里先进行某些操作
            override fun onAnimationStart(animation: Animation) { }
            override fun onAnimationEnd(animation: Animation) { startActivity() }
            override fun onAnimationRepeat(animation: Animation) { }
        })
        iv_start.startAnimation(animation)
    }

    private fun startActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
    }

    override fun onStop() {
        super.onStop()
        bmp!!.recycle()
        System.gc()
    }
}
