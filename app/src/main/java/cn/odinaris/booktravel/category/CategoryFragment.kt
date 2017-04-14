package cn.odinaris.booktravel.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookCategory
import kotlinx.android.synthetic.main.frag_category.*

class CategoryFragment : Fragment(){

    var categoryList = ArrayList<BookCategory>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_category,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val query = BmobQuery<BookCategory>()
        query.addWhereNotEqualTo("parentCategory","")
        query.findObjects(object : FindListener<BookCategory>(){
            override fun done(category: MutableList<BookCategory>?, e: BmobException?) {
                if(category!=null&&e==null){
                    ll_loading.visibility = View.GONE
                    vp_category.visibility = View.VISIBLE
                    categoryList = category as ArrayList<BookCategory>
                    val titles = resources.getStringArray(R.array.parentCategories)
                    val pageAdapter = CategoryTabAdapter(childFragmentManager,titles.toList(),categoryList)
                    vp_category.adapter = pageAdapter
                    tl_category.setupWithViewPager(vp_category)
                }else{
                    Toast.makeText(context,e!!.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
