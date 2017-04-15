package cn.odinaris.booktravel.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R

class BookListFragment(flag:Int) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_category,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
//        initClickListener()//监听器绑定操作
    }

    private fun initView() {

    }

    private fun initData() {

    }
}