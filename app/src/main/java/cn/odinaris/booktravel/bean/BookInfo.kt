package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.datatype.BmobPointer
import cn.bmob.v3.datatype.BmobRelation

class BookInfo : BmobObject(){
    var name = ""//书籍名称
    var ISBN = ""//书籍ISBN编号
    var author = ""//书籍作者
    var category = ""//书籍分类
    var press = ""//书籍出版商
    var belongUser: UserInfo? = null//当前书籍拥有者
    var followedUsers:BmobRelation = BmobRelation()//关注该书的用户列表
    var img1 = ""//用户上传的书籍实物图1
    var img2 = ""//用户上传的书籍实物图2
    var img3 = ""//用户上传的书籍实物图3
    var flag = 0//0表示此书待售，1表示此书用于漂流，2表示此书当前正在借阅中,3表示取消的售书信息
    var author_intro = ""
    var summary = ""
    var pages = ""
    var cover = ""//该书的官方封面图
    /**
     * For Sale
     * */
    var price:Float = 0f//书籍售卖价格，用于待售书籍
    var newPrice:Float = 0f//书籍新书价格，用于待售书籍
    var oldDegree:Float = 10f//书籍新旧程度，10代表全新
    /**
     * For Cross
     * */
    var tripNum = 0//书籍漂流次数
}