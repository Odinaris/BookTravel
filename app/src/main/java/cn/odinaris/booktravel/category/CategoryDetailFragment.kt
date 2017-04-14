package cn.odinaris.booktravel.category

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import cn.odinaris.booktravel.utils.BookListActivity
import cn.odinaris.booktravel.utils.CategoryListAdapter
import cn.odinaris.booktravel.view.CategoryDetailView
import kotlinx.android.synthetic.main.frag_category_detail.*

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
        rv_cat_detail.adapter = CategoryListAdapter(detailCategories, context)
        rv_cat_detail.layoutManager = GridLayoutManager(context,2)
//        for(category:String in detailCategories){
//            val categoryView = CategoryDetailView(context)
//            categoryView.setOnClickListener {
//                startActivity(Intent(activity,BookListActivity::class.java))
//            }
//            categoryView.mFollowNum.visibility = GONE
//            categoryView.mBookNum.visibility = GONE
//            categoryView.cuttingLine.visibility = GONE
//            categoryView.mProgressBar.visibility = GONE
//            categoryView.mCategoryName.text = category
//            //ll_cat_detail.addView(categoryView)
////            val query = BmobQuery<BookCategory>()
////            query.addWhereEqualTo("name",category)
////            query.findObjects(object : FindListener<BookCategory>(){
////                override fun done(p: MutableList<BookCategory>, e: BmobException?) {
////                    categoryView.mFollowNum.text = p[0].followNum.toString()+"人关注"
////                    categoryView.mBookNum.text = p[0].bookNum.toString()+"本书"
////                    categoryView.mProgressBar.visibility = GONE
////                    categoryView.mFollowNum.visibility = VISIBLE
////                    categoryView.mBookNum.visibility = VISIBLE
////                    categoryView.cuttingLine.visibility = VISIBLE
////                }
////
////            })
//        }
    }
}