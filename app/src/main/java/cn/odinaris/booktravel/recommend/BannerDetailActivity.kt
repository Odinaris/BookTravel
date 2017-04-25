package cn.odinaris.booktravel.recommend

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.odinaris.booktravel.R
import kotlinx.android.synthetic.main.banner_detail.*

class BannerDetailActivity : AppCompatActivity() {
    var linkUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.banner_detail)
        linkUrl = intent.getStringExtra("linkUrl")
        wv_banner_detail.setWebViewClient(object:WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
        wv_banner_detail.loadUrl(linkUrl)
    }
}
