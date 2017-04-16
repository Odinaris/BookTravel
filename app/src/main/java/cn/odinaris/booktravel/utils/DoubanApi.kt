package cn.odinaris.booktravel.utils

import cn.odinaris.booktravel.bean.DoubanInfo
import retrofit2.http.GET
import retrofit2.http.Path
import okhttp3.ResponseBody
import retrofit2.Call


/**
 * Created by Odinaris on 2017/4/16.
 */
interface DoubanApi{
    @GET("/v2/book/isbn/:{name}")
    fun getDoubanBookInfo(@Path("name") name:String): Call<DoubanInfo>
}
