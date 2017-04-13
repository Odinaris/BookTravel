package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobObject

class CrossBookInfo(): BmobObject(){
    //漂流书籍信息数据类，用于图书漂流
    var name = ""
    var ISBN = ""
    var author = ""
    var category = ""
    var owner:UserInfo = null!!
    var price = 0

}