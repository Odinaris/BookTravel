package cn.odinaris.booktravel.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

object ImageUtils{
    fun readBitMap(resId: Int,mContext:Context): Bitmap {
        val opt = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.RGB_565
        val `is` = mContext.resources.openRawResource(resId)
        return BitmapFactory.decodeStream(`is`, null, opt)
    }
}