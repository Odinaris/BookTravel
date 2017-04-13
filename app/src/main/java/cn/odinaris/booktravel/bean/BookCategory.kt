package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobObject

class BookCategory: BmobObject(){
    var name = ""
    var parentCategory = ""
    var followNum = 0
    var bookNum = 0
}