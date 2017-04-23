package cn.odinaris.booktravel.publish

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.BmobUser
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.DoubanInfo
import cn.odinaris.booktravel.utils.DoubanApi
import cn.odinaris.booktravel.utils.ImageUtils
import kotlinx.android.synthetic.main.publish_main.*
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class PublishFragment : Fragment(){



    var bmp: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.publish_main,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initClickListener()
    }

    private fun initClickListener() {
        btn_next.setOnClickListener{
            if(BmobUser.getCurrentUser()!=null){
                if(et_isbn.text.toString().length == 13){
                    val retrofit = Retrofit.Builder()
                            .baseUrl("https://api.douban.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    val apiStores = retrofit.create<DoubanApi>(DoubanApi::class.java)
                    val call = apiStores.getDoubanBookInfo(et_isbn.text.toString())
                    call.enqueue(object : Callback<DoubanInfo> {
                        override fun onResponse(call: Call<DoubanInfo>?, response: Response<DoubanInfo>?) {
                            try {
                                if(response!!.isSuccessful){
                                    //将解析后的json对象转换成bean对象并传递给发布详情页
                                    val intent = Intent(context,PublishFormsActivity::class.java)
                                    val bundle = Bundle()
                                    bundle.putSerializable("BookInfo",response.body())
                                    intent.putExtras(bundle)
                                    context.startActivity(intent)
                                }else{ Toast.makeText(context, "请检查ISBN填写是否正确!",Toast.LENGTH_SHORT).show() }
                            } catch (e: IOException) { e.printStackTrace() }
                        }
                        override fun onFailure(call: Call<DoubanInfo>?, t: Throwable?) {
                            if(t!=null) Toast.makeText(context, "onFailure=" + t.message,Toast.LENGTH_SHORT).show()
                        }
                    })
                }
                else{ Toast.makeText(context, "请检查ISBN填写是否正确!",Toast.LENGTH_SHORT).show() }
            }
            else{ Toast.makeText(context, "请登录帐号!",Toast.LENGTH_SHORT).show() }
        }
    }
    private fun initData() {}

    private fun initView() {
        bmp = ImageUtils.readBitMap(R.drawable.book_lamp2,context)
        iv_publish_background.setImageBitmap(bmp)
    }

    override fun onDestroy() {
        super.onDestroy()
        bmp!!.recycle()
        System.gc()
    }

}