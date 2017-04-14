package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobUser

class UserInfo : BmobUser(){
    var studentId:String = ""
    var favoriteCategories:ArrayList<String> = ArrayList()
}