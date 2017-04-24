package cn.odinaris.booktravel.publish

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import cn.odinaris.booktravel.R
import kotlinx.android.synthetic.main.publish_form.*
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import java.io.File
import cn.bmob.v3.listener.UploadFileListener
import cn.odinaris.booktravel.bean.BookInfo
import cn.odinaris.booktravel.main.MainActivity
import cn.bmob.v3.BmobUser
import cn.odinaris.booktravel.bean.DoubanInfo
import cn.odinaris.booktravel.bean.UserInfo
import cn.odinaris.booktravel.utils.PathGetter


class PublishActivity : AppCompatActivity() {

    var type = 0
    var doubanBookInfo = DoubanInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.publish_form)
        initData()
        initView()
        initClickListener()
    }

    private fun initView() {
        //type = 0表示待售，1表示漂流
        if(type!=0){
            ll_publish_price.visibility = View.GONE
            ll_publish_oldDegree.visibility = View.GONE
        }
    }

    private fun initData() {
        type = intent.getIntExtra("type",0)
        doubanBookInfo = intent.getSerializableExtra("BookInfo") as DoubanInfo

    }

    private fun initClickListener() {
        cv_upload.setOnClickListener {
            if(tv_publish_img1.text==""||tv_publish_img2.text==""||tv_publish_img3.text==""){
                val intent = Intent()
                /* 开启Pictures画面Type设定为image */
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1)
            }else{
                Toast.makeText(applicationContext,"3张实物图上传完成，无需继续上传!",Toast.LENGTH_SHORT).show()
            }
        }
        cv_publish.setOnClickListener {
            if(isAllFilled(type)){
                tv_publish.visibility = View.GONE
                pb_publish.visibility = View.VISIBLE
                val newBookInfo = BookInfo()
                newBookInfo.ISBN = doubanBookInfo.isbn13
                newBookInfo.name = doubanBookInfo.title
                newBookInfo.author = doubanBookInfo.author.toString()
                if(doubanBookInfo.price.contains("元")){
                    newBookInfo.newPrice =doubanBookInfo.price.split("元")[0].toFloat()
                }else{
                    newBookInfo.newPrice =doubanBookInfo.price.toFloat()
                }
                newBookInfo.category = et_publish_category.text.toString()
                newBookInfo.img1 = tv_publish_img1.text.toString()
                newBookInfo.img2 = tv_publish_img2.text.toString()
                newBookInfo.img3 = tv_publish_img3.text.toString()
                newBookInfo.author_intro = doubanBookInfo.author_intro
                newBookInfo.summary = doubanBookInfo.summary
                newBookInfo.cover = doubanBookInfo.image
                newBookInfo.press = doubanBookInfo.publisher
                newBookInfo.pages = doubanBookInfo.pages
                if(type==0){
                    newBookInfo.price = et_book_price.text.toString().toFloat()
                    newBookInfo.oldDegree = et_publish_oldDegree.text.toString().toFloat()
                    newBookInfo.flag = 0
                }else{
                    newBookInfo.flag = 1
                    newBookInfo.tripNum = 1
                }
                val user = BmobUser.getCurrentUser<UserInfo>(UserInfo::class.java)
                newBookInfo.belongUser = user
                newBookInfo.save(object:SaveListener<String>(){
                    override fun done(p0: String?, e: BmobException?) {
                        if(e == null){
                            tv_publish.text = "提交完成"
                            pb_publish.visibility = View.GONE
                            tv_publish.visibility = View.VISIBLE
                            val dialog = AlertDialog.Builder(this@PublishActivity)
                            dialog.setMessage("信息提交成功!").setPositiveButton("OK",
                                    { dialog, which ->
                                        startActivity(Intent(this@PublishActivity,MainActivity::class.java))
                                        finish()
                                    }).show()
                        }else{
                            Toast.makeText(applicationContext,"提交失败" + e.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else{ Toast.makeText(applicationContext,"信息填写不完整!",Toast.LENGTH_SHORT).show() }
        }
    }

    private fun isAllFilled(type:Int): Boolean {
        if(et_publish_category.text.toString() =="") return false
        if(tv_publish_img1.text.toString() =="") return false
        if(tv_publish_img2.text.toString() =="") return false
        if(tv_publish_img3.text.toString() =="") return false
        if(type == 0){
            if(et_book_price.text.toString() =="") return false
            if(et_publish_oldDegree.text.toString() =="") return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if(data!=null){
                val pic_path = data.data
                val file = BmobFile(File(PathGetter.getPath(this,pic_path)))
                if(tv_publish_img1.text=="") {
                    pb_img1.visibility = View.VISIBLE
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                pb_img1.visibility = View.GONE
                                tv_publish_img1.text = file.fileUrl
                                tv_publish_img1.visibility = View.VISIBLE
                            } else {
                                Toast.makeText(applicationContext,"上传文件失败：" + pic_path+ e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onProgress(value: Int?) { }
                    })
                }
                else if(tv_publish_img2.text==""){
                    pb_img2.visibility = View.VISIBLE
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                pb_img2.visibility = View.GONE
                                tv_publish_img2.text = file.fileUrl
                                tv_publish_img2.visibility = View.VISIBLE
                            } else {
                                Toast.makeText(applicationContext,"上传文件失败：" + e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onProgress(value: Int?) { }
                    })
                }
                else if(tv_publish_img3.text==""){
                    pb_img3.visibility = View.VISIBLE
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                pb_img3.visibility = View.GONE
                                tv_publish_img3.text = file.fileUrl
                                tv_publish_img3.visibility = View.VISIBLE
                            } else {
                                Toast.makeText(applicationContext,"上传文件失败：" + e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onProgress(value: Int?) { }
                    })
                }
            }
        }
    }


}
