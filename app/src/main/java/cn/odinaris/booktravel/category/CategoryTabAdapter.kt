package cn.odinaris.booktravel.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.odinaris.booktravel.bean.BookCategory

class CategoryTabAdapter(fm: FragmentManager, var parentCategory: List<String>, var categoryList: ArrayList<BookCategory>): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        val selectedCategoryList = ArrayList<BookCategory>()
        categoryList.filterTo(selectedCategoryList) { it.parentCategory == parentCategory[position] }
        return CategoryDetailFragment(selectedCategoryList)
    }

    override fun getCount(): Int { return parentCategory.size }

    override fun getPageTitle(position: Int): CharSequence { return parentCategory[position] }

}