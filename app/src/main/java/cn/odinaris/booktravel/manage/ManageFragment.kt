package cn.odinaris.booktravel.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.category.ManageTabAdapter
import kotlinx.android.synthetic.main.frag_manage.*

class ManageFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_manage,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val titles = resources.getStringArray(R.array.manageList)
        val pageAdapter = ManageTabAdapter(childFragmentManager,titles.toList())
        vp_manage.adapter = pageAdapter
        tl_manage.setupWithViewPager(vp_manage)
    }
}
