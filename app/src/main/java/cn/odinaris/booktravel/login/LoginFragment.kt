package cn.odinaris.booktravel.login

import android.support.v4.app.Fragment
import cn.bmob.v3.Bmob
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import cn.odinaris.booktravel.R


class LoginFragment(val type:String): Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_login_type, container, false)
        Bmob.initialize(activity, "6590c04360b490a8625cebf8826457b3")
        initView()

        return view
    }

    private fun initView() {

    }
}