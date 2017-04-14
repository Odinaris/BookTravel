package cn.odinaris.booktravel.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Window
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.category.CategoryFragment
import cn.odinaris.booktravel.home.HomeFragment
import cn.odinaris.booktravel.login.LoginActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {
    val fragmentsList = ArrayList<Fragment>()
    val home = HomeFragment()
    val category = CategoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.act_main)
        initData()
        setDefaultFragment()
        initView()
    }

    private fun initData() {

    }

    private fun initView() {
        fragmentsList.add(0, home)
        fragmentsList.add(1, category)
        fragmentsList.add(2, home)
        fragmentsList.add(3, home)
        fragmentsList.add(4, home)
        bnb_navigator
                .addItem(BottomNavigationItem(R.drawable.ic_home,"主页"))
                .addItem(BottomNavigationItem(R.drawable.ic_category,"分类"))
                .addItem(BottomNavigationItem(R.drawable.ic_publish,"发布"))
                .addItem(BottomNavigationItem(R.drawable.ic_manage,"管理"))
                .addItem(BottomNavigationItem(R.drawable.ic_user,"我"))
                .setFirstSelectedPosition(0).initialise()
        bnb_navigator.setTabSelectedListener(object: BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) { }

            override fun onTabUnselected(position: Int) { }

            override fun onTabSelected(position: Int) {
                val SFM = supportFragmentManager!!
                val transaction = SFM.beginTransaction()!!
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                hideAllFragments(transaction)
                when(position){
                    0 -> {
                        if (!home.isAdded) {
                            transaction.add(R.id.ll_container, home).show(home)
                        } else {
                            transaction.show(home) }
                    }
                    1 -> {
                        if (!category.isAdded) {
                            transaction.add(R.id.ll_container, category).show(category)
                        } else {
                            transaction.show(category) }
                    }
                    2 -> {
                        if (!home.isAdded) {
                            transaction.add(R.id.ll_container, home).show(home)
                        } else {
                            transaction.show(home) }
                    }
                    3 -> {
                        if (!home.isAdded) {
                            transaction.add(R.id.ll_container, home).show(home)
                        } else {
                            transaction.show(home) }
                    }
                }
                transaction.commit()
            }
        })
       // test.setOnClickListener { startActivity(Intent(this,LoginActivity::class.java)) }
    }

    private fun setDefaultFragment() {
        if(!home.isAdded){
            val SFM = supportFragmentManager!!
            val transaction = SFM.beginTransaction()!!
            transaction.add(R.id.ll_container, home)
            transaction.commit()
        }
    }

    private fun hideAllFragments(transaction: FragmentTransaction) {
        for (i in fragmentsList.indices) { transaction.hide(fragmentsList[i]) }
    }
}
