package cn.odinaris.booktravel.login

import android.support.v4.app.Fragment
import cn.bmob.v3.Bmob
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import cn.odinaris.booktravel.R
import kotlinx.android.synthetic.main.frag_login_type.*


class LoginFragment(val type:String): Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_login_type, container, false)
        return view
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
        //initClickListener()//监听器绑定操作
    }

    private fun initView() {
        if(type == "登陆"){
            tv_loginOrRegister.text = "登陆"
        }else{
            tv_loginOrRegister.text = "注册"
            tv_forget.visibility = View.GONE
        }

    }
}