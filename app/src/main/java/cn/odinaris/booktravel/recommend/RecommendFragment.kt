package cn.odinaris.booktravel.recommend

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BannerList
import cn.odinaris.booktravel.bean.BookCategory
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.bean.UserInfo
import cn.odinaris.booktravel.utils.DisScrollGridLayoutManager
import cn.odinaris.booktravel.utils.GlideImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.recommend_main.*
import me.odinaris.booktravel.utils.DisScrollLinearLayoutManager
import kotlin.collections.ArrayList


class RecommendFragment : Fragment(){
    val REFRESH_COMPLETE = 0
    private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message){
            when(msg.what){
                REFRESH_COMPLETE -> {
                    findFollowed()
                    findBanners()
                    srl_refresh.isRefreshing = false
                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.recommend_main,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initData()//网络加载、数据请求操作
        initView()


    }

    private fun initView() {
        srl_refresh.setOnRefreshListener {
            handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000)

        }

    }

    private fun initData() {
        srl_refresh.isRefreshing = true
        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2500)
    }

    //查找关注的书籍列表
    private fun findFollowed() {
        //建立内部查询
        val innerQuery = BmobQuery<UserInfo>()
        innerQuery.addWhereEqualTo("objectId",BmobUser.getCurrentUser().objectId)
        val followedQuery = BmobQuery<BookCategory>()
        followedQuery.addWhereMatchesQuery("followedUsers","_User",innerQuery)
        followedQuery.findObjects(object : FindListener<BookCategory>(){
            override fun done(categories: MutableList<BookCategory>?, e: BmobException?) {
                if(e == null && categories != null){
                    val categoryQuery = BmobQuery<BookInfo>()
                    val flagQuery = BmobQuery<BookInfo>()
                    val queryAnd = ArrayList<BmobQuery<BookInfo>>()
                    val bookQuery = BmobQuery<BookInfo>()
                    val categoryList = ArrayList<String>()
                    val flagType = ArrayList<Int>()
                    flagType.add(0)
                    flagType.add(1)
                    categories.mapTo(categoryList) { it.name }
                    //设置只显示flag = 0 or 1的书籍
                    categoryQuery.addWhereContainedIn("category", categoryList)
                    flagQuery.addWhereContainedIn("flag", flagType)
                    queryAnd.add(categoryQuery)
                    queryAnd.add(flagQuery)
                    bookQuery.and(queryAnd)
                    bookQuery.findObjects(object : FindListener<BookInfo>(){
                        override fun done(books: MutableList<BookInfo>?, e1: BmobException?) {
                            if(e1==null){
                                val bookList = books as ArrayList<BookInfo>
                                val manager = DisScrollLinearLayoutManager(context)
                                manager.isScrollEnabled = false
                                rv_hot_list.adapter = RecommendAdapter(bookList, categoryList,context)
                                rv_hot_list.layoutManager = manager
                                rv_hot_list.visibility = View.VISIBLE
                            }else{ Toast.makeText(context,e1.message,Toast.LENGTH_SHORT).show() }
                        }
                    })
                }
            }
        })
    }
    //查找Banner信息
    private fun findBanners() {
        val bannerQuery = BmobQuery<BannerList>()
        bannerQuery.addWhereNotEqualTo("title","").order("-UpdatedAt").setLimit(3)
        bannerQuery.findObjects(object:FindListener<BannerList>(){
            override fun done(banners: MutableList<BannerList>?, e: BmobException?) {
                if(e==null){
                    val images = ArrayList<String>()
                    val titles = ArrayList<String>()
                    //var links = ArrayList<String>()
                    banners!!.mapTo(images){it.imageUrl}
                    banners.mapTo(titles){it.title}
                    //banners.mapTo(images){it.linkUrl}
                    banner.setImageLoader(GlideImageLoader)
                            .setImages(images)
                            .setBannerTitles(titles)
                            .setBannerAnimation(Transformer.DepthPage)
                            .setDelayTime(2000)
                            .setIndicatorGravity(BannerConfig.RIGHT)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                            .isAutoPlay(true)
                            .start()
//                    banner.setOnBannerListener {
//
//                    }
                }else{
                    Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
                }
            }

        })

    }


    override fun onStart() {
        super.onStart()
        //开始轮播
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        //结束轮播
        banner.stopAutoPlay()
    }
}
