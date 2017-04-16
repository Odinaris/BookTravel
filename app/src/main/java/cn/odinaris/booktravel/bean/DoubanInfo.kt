package cn.odinaris.booktravel.bean

import java.io.Serializable

class DoubanInfo:Serializable{
    inner class Rating :Serializable{
        var max: Int = 0
        var numRaters: Int = 0
        var average: String = ""
        var min: Int = 0
    }
    inner class Images :Serializable{
        var small: String = ""
        var large: String = ""
        var medium: String = ""
    }
    inner class Tags :Serializable{
        var count: Int = 0
        var name: String = ""
        var title: String = ""
    }

    var rating: Rating? = null
    var subtitle: String = ""
    var author: List<String>? = null
    var tags: List<Tags>? = null
    var pubdate: String = ""
    var origin_title: String = ""
    var image: String = ""
    var binding: String = ""
    var translator: List<String>? = null
    var catalog: String = ""
    var pages: String = ""
    var alt: String = ""
    var images: Images? = null
    var id: String = ""
    var publisher: String = ""
    var isbn10: String = ""
    var isbn13: String = ""
    var title: String = ""
    var url: String = ""
    var alt_title: String = ""
    var author_intro: String = ""
    var summary: String = ""
    var price: String = ""


}
