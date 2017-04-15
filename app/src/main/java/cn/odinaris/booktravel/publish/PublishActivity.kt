package cn.odinaris.booktravel.publish

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import cn.odinaris.booktravel.R
import kotlinx.android.synthetic.main.act_publish.*
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
import cn.odinaris.booktravel.bean.UserInfo
import cn.odinaris.booktravel.utils.PathGetter


class PublishActivity : AppCompatActivity() {

    var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_publish)
        initData()
        initView()
        initClickListener()
    }

    private fun initView() {
        //type = 0表示待售，1表示漂流
        if(type!=0){
            ll_book_price.visibility = View.GONE
            ll_book_newPrice.visibility = View.GONE
            ll_book_oldDegree.visibility = View.GONE
        }
    }

    private fun initData() {
        type = intent.getIntExtra("type",0)
    }

    private fun initClickListener() {
        btn_upload.setOnClickListener {
            if(tv_book_img1.text==""||tv_book_img2.text==""||tv_book_img3.text==""){
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
        tv_submit.setOnClickListener {
            if(isAllFilled(type)){
                tv_submit.visibility = View.GONE
                pb_submit.visibility = View.VISIBLE
                val newBookInfo = BookInfo()
                newBookInfo.name = et_book_name.text.toString()
                newBookInfo.author = et_book_author.text.toString()
                newBookInfo.press = et_book_press.text.toString()
                newBookInfo.ISBN = et_book_isbn.text.toString()
                newBookInfo.category = et_book_category.text.toString()
                newBookInfo.img1 = tv_book_img1.text.toString()
                newBookInfo.img2 = tv_book_img2.text.toString()
                newBookInfo.img3 = tv_book_img3.text.toString()
                if(type==0){
                    newBookInfo.price = Integer.parseInt(et_book_price.text.toString())
                    newBookInfo.newPrice = Integer.parseInt(et_book_newPrice.text.toString())
                    newBookInfo.oldDegree = Integer.parseInt(et_book_oldDegree.text.toString())
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
                            tv_submit.text = "提交完成"
                            pb_submit.visibility = View.GONE
                            tv_submit.visibility = View.VISIBLE
                            val dialog = AlertDialog.Builder(this@PublishActivity)
                            dialog.setMessage("信息提交成功!").setPositiveButton("OK",
                                    { dialog, which ->
                                        startActivity(Intent(this@PublishActivity,MainActivity::class.java))
                                    }).show()
                        }else{
                            Toast.makeText(applicationContext,"提交失败" + e.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            else{

            }
        }
    }

    private fun isAllFilled(type:Int): Boolean {
        if(et_book_name.text.toString() =="") return false
        if(et_book_author.text.toString() =="") return false
        if(et_book_press.text.toString() =="") return false
        if(et_book_isbn.text.toString() =="") return false
        if(et_book_category.text.toString() =="") return false
        if(tv_book_img1.text.toString() =="") return false
        if(tv_book_img2.text.toString() =="") return false
        if(tv_book_img3.text.toString() =="") return false
        if(type == 0){
            if(et_book_price.text.toString() =="") return false
            if(et_book_newPrice.text.toString() =="") return false
            if(et_book_oldDegree.text.toString() =="") return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if(data!=null){
                val pic_path = data.data
                val file = BmobFile(File(PathGetter.getPath(this,pic_path)))
                if(tv_book_img1.text=="") {
                    pb_img1.visibility = View.VISIBLE
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                pb_img1.visibility = View.GONE
                                tv_book_img1.text = file.fileUrl
                                tv_book_img1.visibility = View.VISIBLE
                            } else {
                                Toast.makeText(applicationContext,"上传文件失败：" + pic_path+ e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onProgress(value: Int?) { }
                    })
                }
                else if(tv_book_img2.text==""){
                    pb_img2.visibility = View.VISIBLE
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                pb_img2.visibility = View.GONE
                                tv_book_img2.text = file.fileUrl
                                tv_book_img2.visibility = View.VISIBLE
                            } else {
                                Toast.makeText(applicationContext,"上传文件失败：" + e.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onProgress(value: Int?) { }
                    })
                }
                else if(tv_book_img3.text==""){
                    pb_img3.visibility = View.VISIBLE
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                pb_img3.visibility = View.GONE
                                tv_book_img3.text = file.fileUrl
                                tv_book_img3.visibility = View.VISIBLE
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
