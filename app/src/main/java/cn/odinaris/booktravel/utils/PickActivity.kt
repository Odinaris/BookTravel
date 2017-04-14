package cn.odinaris.booktravel.utils

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener

import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import cn.odinaris.booktravel.bean.UserInfo

import kotlinx.android.synthetic.main.act_pick.*
import me.odinaris.booktravel.utils.DisScrollLinearLayoutManager

class PickActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.act_pick)
        initView()
        initData()
    }

    private fun initData() {
        val query = BmobQuery<BookCategory>()
        query.addWhereNotEqualTo("name","")
        query.findObjects(object : FindListener<BookCategory>(){
            override fun done(category: MutableList<BookCategory>?, e: BmobException?) {
                if(category!=null&&e==null){
                    val innerQuery = BmobQuery<UserInfo>()
                    innerQuery.addWhereEqualTo("objectId", BmobUser.getCurrentUser().objectId)
                    val categoryQuery = BmobQuery<BookCategory>()
                    categoryQuery.addWhereMatchesQuery("followedUsers","_User",innerQuery)
                    categoryQuery.findObjects(object : FindListener<BookCategory>(){
                        override fun done(followedCategories: MutableList<BookCategory>, e1: BmobException?) {
                            if(e1 == null){
                                val followedList = followedCategories as ArrayList<BookCategory>
                                val categoryList = category as ArrayList<BookCategory>
                                val adapter = PickAdapter(categoryList,applicationContext)
                                val manager = DisScrollLinearLayoutManager(applicationContext)
                                pb_loading.visibility = View.GONE
                                adapter.setFollowedCategories(followedList)
                                rv_categories.adapter = adapter
                                manager.isScrollEnabled = false
                                rv_categories.layoutManager = manager
                                rv_categories.visibility = View.VISIBLE
                            }

                        }
                    })
                }else if(e!=null){
                    val dialog = AlertDialog.Builder(this@PickActivity)
                    dialog.setMessage(e.message).show()
                }
            }

        })
    }

    private fun initView() {

    }

}
