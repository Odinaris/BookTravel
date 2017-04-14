package cn.odinaris.booktravel.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R

class CategoryDetailFragment(var type:String):Fragment(){
    var detailCategories:List<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_category_detail, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
        //initClickListener()//监听器绑定操作

    }

    private fun initData() {
        when(type){
            "文艺" -> detailCategories = resources.getStringArray(R.array.cat_literature_art).asList()
            "人文社科" -> detailCategories = resources.getStringArray(R.array.cat_culture_social).asList()
            "经济管理" -> detailCategories = resources.getStringArray(R.array.cat_economic_management).asList()
            "励志" -> detailCategories = resources.getStringArray(R.array.cat_motivational).asList()
            "教育" -> detailCategories = resources.getStringArray(R.array.cat_education).asList()
            "科技" -> detailCategories = resources.getStringArray(R.array.cat_science).asList()
            "生活" -> detailCategories = resources.getStringArray(R.array.cat_life).asList()
        }
    }

    private fun initView() {
        for(category:String in detailCategories){
            var card = CardView(context)
        }
    }
}