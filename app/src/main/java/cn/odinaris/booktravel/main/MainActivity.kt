package cn.odinaris.booktravel.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Window
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.category.CategoryFragment
import cn.odinaris.booktravel.recommend.RecommendFragment
import cn.odinaris.booktravel.recommend.ManageFragment
import cn.odinaris.booktravel.publish.PublishFragment
import cn.odinaris.booktravel.user.UserFragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.main_act.*

class MainActivity : AppCompatActivity() {
    val fragmentsList = ArrayList<Fragment>()
    val home = RecommendFragment()
    val category = CategoryFragment()
    val publish = PublishFragment()
    val manage = ManageFragment()
    val user = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.main_act)
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
            override fun onTabReselected(position: Int) { }

            override fun onTabUnselected(position: Int) { }

            override fun onTabSelected(position: Int) {
                val SFM = supportFragmentManager!!
                val transaction = SFM.beginTransaction()!!
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                hideAllFragments(transaction)
                when(position){
                    0 -> {
                        if (!home.isAdded) { transaction.add(R.id.ll_container, home).show(home) }
                        else { transaction.show(home) }
                    }
                    1 -> {
                        if (!category.isAdded) { transaction.add(R.id.ll_container, category).show(category) }
                        else { transaction.show(category) }
                    }
                    2 -> {
                        if (!publish.isAdded) { transaction.add(R.id.ll_container, publish).show(publish) }
                        else { transaction.show(publish) }
                    }
                    3 -> {
                        if (!manage.isAdded) { transaction.add(R.id.ll_container, manage).show(manage) }
                        else { transaction.show(manage) }
                    }
                    4 -> {
                        if (!user.isAdded) { transaction.add(R.id.ll_container, user).show(user) }
                        else { transaction.show(user) }
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
