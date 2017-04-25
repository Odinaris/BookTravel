package cn.odinaris.booktravel.bookinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.BookInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.book_images.*

class BookCrossFragment(val book: String) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.book_cross,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

    }
}