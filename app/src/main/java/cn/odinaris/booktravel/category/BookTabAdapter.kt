package cn.odinaris.booktravel.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class BookTabAdapter(fm: FragmentManager, val category:String, var bookType: List<String>): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment { return BookListFragment(position,category) }

    override fun getCount(): Int { return bookType.size }

    override fun getPageTitle(position: Int): CharSequence { return bookType[position] }

}