package cn.odinaris.booktravel.utils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.view.View.OnClickListener

import cn.odinaris.booktravel.R

import kotlinx.android.synthetic.main.act_pick.*

class PickActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_pick)
        initView()
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
