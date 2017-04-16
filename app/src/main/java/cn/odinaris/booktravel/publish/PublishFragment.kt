package cn.odinaris.booktravel.publish

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.utils.ImageUtils
import kotlinx.android.synthetic.main.frag_publish.*


class PublishFragment : Fragment(){

    var bmp: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_publish,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

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