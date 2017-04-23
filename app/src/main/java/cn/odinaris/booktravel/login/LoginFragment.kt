package cn.odinaris.booktravel.login

import android.app.AlertDialog
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.UserInfo
import kotlinx.android.synthetic.main.login_type.*
import cn.odinaris.booktravel.main.MainActivity
import android.content.Intent
import cn.odinaris.booktravel.picker.PickActivity


class LoginFragment(val type:String): Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.login_type, container, false)
        return view
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initView()//适配器绑定等操作
        initClickListener()//监听器绑定操作
    }

    private fun initClickListener() {
        cv_loginOrRegister.setOnClickListener {
            if(isInfoFilled(et_username,et_password)){
                val userInfo = UserInfo()
                userInfo.username = et_username.text.toString()
                userInfo.setPassword(et_password.text.toString())
                if(type == "登陆"){
                    userInfo.login(object:SaveListener<UserInfo>(){
                        override fun done(user: UserInfo?, e: BmobException?) {
                            if(e == null){
                                Snackbar.make(ll_container,"登陆成功!",Snackbar.LENGTH_SHORT)
                                        .addCallback(object: Snackbar.Callback() {
                                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                                super.onDismissed(transientBottomBar, event)
                                                startActivity(Intent(activity,MainActivity::class.java))
                                            }
                                        }).show()
                            }else{
                                val dialog = AlertDialog.Builder(context)
                                dialog.setTitle("登陆失败!")
                                        .setMessage(e.message).setPositiveButton("知道了",null).show()
                            }
                        }
                    })
                }else{
                    userInfo.signUp(object:SaveListener<UserInfo>(){
                        override fun done(user: UserInfo?, e: BmobException?) {
                            if(e == null){
                                Snackbar.make(ll_container,"注册成功!",Snackbar.LENGTH_SHORT)
                                        .addCallback(object: Snackbar.Callback() {
                                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                                super.onDismissed(transientBottomBar, event)
                                                startActivity(Intent(activity, PickActivity::class.java))
                                            }
                                        }).show()
                            }else{
                                val dialog = AlertDialog.Builder(context)
                                dialog.setTitle("注册失败!")
                                        .setMessage(e.message).setPositiveButton("知道了",null).show()
                            }
                        }
                    })
                }
            }
        }
    }

    //检查用户信息是否填写完整
    private fun isInfoFilled(et_username: EditText, et_password: EditText): Boolean {
        return et_username.text!=null&&et_password.text!=null
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