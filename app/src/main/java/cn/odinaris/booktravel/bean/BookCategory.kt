package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobRelation

class BookCategory: BmobObject(){
    var name = ""
    var parentCategory = ""
    var followNum = 0
    var bookNum = 0
    var followedUsers: BmobRelation = BmobRelation()//关注当前分类的用户索引信息
    var categoriyUrl = ""//分类图片Url
}