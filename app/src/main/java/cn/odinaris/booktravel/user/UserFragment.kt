package cn.odinaris.booktravel.user

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobUser
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.login.LoginActivity
import cn.odinaris.booktravel.picker.PickActivity
import kotlinx.android.synthetic.main.frag_user.*


class UserFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_user,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
//        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
        initClickListener()//监听器绑定操作
    }

    private fun initView() {}

    private fun initClickListener() {
        cv_tags.setOnClickListener {
            startActivity(Intent(activity, PickActivity::class.java))
        }
        cv_log_out.setOnClickListener {
            if(BmobUser.getCurrentUser()!=null){
                BmobUser.logOut()
                startActivity(Intent(activity,LoginActivity::class.java))
            }
        }
    }

}
