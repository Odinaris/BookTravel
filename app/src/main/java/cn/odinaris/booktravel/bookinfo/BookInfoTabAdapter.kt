package cn.odinaris.booktravel.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bookinfo.BookDetailFragment
import cn.odinaris.booktravel.bookinfo.BookImageFragment

class BookInfoTabAdapter(fm: FragmentManager, var book:BookInfo, var titles: List<String>): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        if(position == 0){
            return(BookDetailFragment(book))
        }
        else if(position == 1){
            return(BookImageFragment(book))
        }
        else{
            return(BookDetailFragment(book))
        }
    }

    override fun getCount(): Int { return titles.size }

    override fun getPageTitle(position: Int): CharSequence { return titles[position] }

}