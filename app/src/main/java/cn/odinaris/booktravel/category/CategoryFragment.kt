package cn.odinaris.booktravel.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import kotlinx.android.synthetic.main.frag_category.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView
import kotlin.collections.ArrayList


class CategoryFragment : Fragment(){
    var categoryList = ArrayList<BookCategory>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_category,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
        initClickListener()//监听器绑定操作
    }

    private fun initData() {

    }

    private fun initClickListener() {}

    private fun initView() {
        val titles = resources.getStringArray(R.array.parentCategories)
        val pageAdapter = CategoryAdapter(childFragmentManager,titles.toList())
        vp_category.adapter = pageAdapter
        tl_category.setupWithViewPager(vp_category)
    }

}
