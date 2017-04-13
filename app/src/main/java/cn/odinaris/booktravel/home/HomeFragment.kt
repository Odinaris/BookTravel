package cn.odinaris.booktravel.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import java.util.*
import android.content.Context
import cn.odinaris.booktravel.R


class HomepageFragment : Fragment(),View.OnClickListener{
    override fun onClick(v: View?) {

    }

    private val REQUEST_CODE_PICK_CITY = 0
    //private var carList: ArrayList<CarIntro>? = ArrayList()
    private var viewList: ArrayList<View> = ArrayList()
    private val loadingNum = 10
    private val skipNum = 0
    private var carMap:HashMap<String,String> = HashMap()
    private var  bnb: BottomNavigationBar? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_home,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
//        initData()//网络加载、数据请求操作
//        initView()//适配器绑定等操作
//        initClickListener()//监听器绑定操作
    }

}
