package cn.odinaris.booktravel.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore

object ImageUtils{
    fun readBitMap(resId: Int,mContext:Context): Bitmap {
        val opt = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.RGB_565
        val `is` = mContext.resources.openRawResource(resId)
        return BitmapFactory.decodeStream(`is`, null, opt)
    }
}