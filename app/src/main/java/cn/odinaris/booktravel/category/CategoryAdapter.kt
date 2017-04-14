package cn.odinaris.booktravel.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class CategoryAdapter(fm: FragmentManager, var titles:List<String>): FragmentStatePagerAdapter(fm){
    //返回当前选择登陆类型(登陆/注册)
    override fun getItem(position: Int): Fragment { return CategoryDetailFragment(titles[position]) }

    override fun getCount(): Int { return titles.size }

    override fun getPageTitle(position: Int): CharSequence { return titles[position] }

}