package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobObject

class SaleBookInfo(): BmobObject(){
    //待售书籍信息数据类，用于交易
    var name = ""
    var ISBN = ""
    var author = ""
    var category = ""
    var owner:UserInfo = null!!
    var price = 0

}