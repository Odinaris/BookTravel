package cn.odinaris.booktravel.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.odinaris.booktravel.manage.ManageListFragment

class ManageTabAdapter(fm: FragmentManager, var parentCategory: List<String>): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment { return ManageListFragment(position) }

    override fun getCount(): Int { return parentCategory.size }

    override fun getPageTitle(position: Int): CharSequence { return parentCategory[position] }

}

