package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobUser

class UserInfo : BmobUser(){
    var studentId:String = ""
    var followCategory:ArrayList<String> = ArrayList()
}