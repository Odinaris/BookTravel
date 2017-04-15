package cn.odinaris.booktravel.main

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.category.CategoryFragment
import cn.odinaris.booktravel.home.HomeFragment
import cn.odinaris.booktravel.home.ManageFragment
import cn.odinaris.booktravel.publish.PublishActivity
import cn.odinaris.booktravel.publish.PublishFragment
import cn.odinaris.booktravel.user.UserFragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : AppCompatActivity() {
    val fragmentsList = ArrayList<Fragment>()
    val home = HomeFragment()
    val category = CategoryFragment()
    val publish = PublishFragment()
    val manage = ManageFragment()
    val user = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.BLACK
        setContentView(R.layout.act_main)
        initView()
        setDefaultFragment()
    }

    private fun initView() {
        fragmentsList.add(0, home)
        fragmentsList.add(1, category)
        fragmentsList.add(2, manage)
        fragmentsList.add(3, publish)
        fragmentsList.add(4, user)
        bnb_navigator
                .addItem(BottomNavigationItem(R.drawable.ic_home,"主页"))
                .addItem(BottomNavigationItem(R.drawable.ic_category,"分类"))
                .addItem(BottomNavigationItem(R.drawable.ic_publish,"发布"))
                .addItem(BottomNavigationItem(R.drawable.ic_manage,"管理"))
                .addItem(BottomNavigationItem(R.drawable.ic_user,"我"))
                .setFirstSelectedPosition(0).initialise()
        bnb_navigator.setTabSelectedListener(object: BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
//                when(position){
//                    2 -> {
//                        val dialog = AlertDialog.Builder(this@MainActivity).create()
//                        dialog.show()
//                        val window = dialog.window
//                        window.setContentView(R.layout.dialog_publish)
//                        val saleCard = window.findViewById(R.id.cv_saleBook) as CardView
//                        val crossCard = window.findViewById(R.id.cv_crossBook) as CardView
//                        saleCard.setOnClickListener {
//                            val intent = Intent(this@MainActivity, PublishActivity::class.java)
//                            intent.putExtra("type",0)
//                            startActivity(intent)
//                        }
//                        crossCard.setOnClickListener {
//                            val intent = Intent(this@MainActivity, PublishActivity::class.java)
//                            intent.putExtra("type",1)
//                            startActivity(intent)
//                        }
//                    }
//                }
            }

            override fun onTabUnselected(position: Int) {
//                when(position){
//                    2 -> {
//                        ll_container.setBackgroundColor(Color.WHITE)
//                    }
//                }
            }

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
                        if (!publish.isAdded) {
                            transaction.add(R.id.ll_container, publish).show(publish)
                        } else {
                            transaction.show(publish) }
//                        val dialog = AlertDialog.Builder(this@MainActivity).create()
//                        dialog.show()
//                        val window = dialog.window
//                        window.setContentView(R.layout.dialog_publish)
//                        val saleCard = window.findViewById(R.id.cv_saleBook) as CardView
//                        val crossCard = window.findViewById(R.id.cv_crossBook) as CardView
//                        saleCard.setOnClickListener {
//                            val intent = Intent(this@MainActivity, PublishActivity::class.java)
//                            intent.putExtra("type",0)
//                            startActivity(intent)
//                        }
//                        crossCard.setOnClickListener {
//                            val intent = Intent(this@MainActivity, PublishActivity::class.java)
//                            intent.putExtra("type",1)
//                            startActivity(intent)
//                        }
                    }
                    3 -> {
                        if (!manage.isAdded) {
                            transaction.add(R.id.ll_container, manage).show(manage)
                        } else {
                            transaction.show(manage) }
                    }
                    4 -> {
                        if (!user.isAdded) {
                            transaction.add(R.id.ll_container, user).show(user)
                        } else {
                            transaction.show(user) }
                    }

                }
                transaction.commit()
            }
        })
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
