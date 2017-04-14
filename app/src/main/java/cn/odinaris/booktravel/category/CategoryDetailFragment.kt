package cn.odinaris.booktravel.category

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import cn.odinaris.booktravel.utils.CategoryListAdapter
import kotlinx.android.synthetic.main.frag_category_detail.*

class CategoryDetailFragment(var selectedCategoryList: ArrayList<BookCategory>):Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_category_detail, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作

    }
    private fun initData() {
        rv_cat_detail.adapter = CategoryListAdapter(selectedCategoryList, context)
        rv_cat_detail.layoutManager = GridLayoutManager(context,2)
    }
    private fun initView() {  }
}