package cn.odinaris.booktravel.login

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class LoginAdapter(fm:FragmentManager, var titles:List<String>): FragmentStatePagerAdapter(fm){


    override fun getItem(position: Int): Fragment {
        return LoginFragment()
    }

    override fun getCount(): Int {
        return titles.size
    }

}