package cn.odinaris.booktravel.bean

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.datatype.BmobRelation

class BookInfo : BmobObject(){
    var name = ""//书籍名称
    var ISBN = ""//书籍ISBN编号
    var author = ""//书籍作者
    var category = ""//书籍分类
    var press = ""//书籍出版商
    var owner:UserInfo = UserInfo()//书籍当前拥有者
    var followedNum:Int = 0//关注该书的人数
    var commentsNum:Int = 0//评论该书的人数
    var follower:BmobRelation = BmobRelation()//关注该书的用户列表
    var comments:BmobRelation = BmobRelation()//评论该书的用户列表
    var coverUrl = ""//该书的官方封面图
    var img1:BmobFile = BmobFile()//用户上传的书籍实物图1
    var img2:BmobFile = BmobFile()//用户上传的书籍实物图2
    var img3:BmobFile = BmobFile()//用户上传的书籍实物图3
    var flag = 0//0表示此书待售，1表示此书用于漂流，2表示此书当前正在借阅中,3表示取消的售书信息
    /**
     * For Sale
     * */
    var price:Int = 0//书籍售卖价格，用于待售书籍
    var newPrice:Int = 0//书籍新书价格，用于待售书籍
    var oldDegree = 10//书籍新旧程度，10代表全新
    /**
     * For Cross
     * */
    var tripNum = 0//书籍漂流次数
}