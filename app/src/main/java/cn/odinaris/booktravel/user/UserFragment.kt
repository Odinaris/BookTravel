package cn.odinaris.booktravel.user

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import cn.bmob.v3.listener.UploadFileListener
import cn.odinaris.booktravel.R
import cn.odinaris.booktravel.bean.UserInfo
import cn.odinaris.booktravel.login.LoginActivity
import cn.odinaris.booktravel.picker.PickActivity
import cn.odinaris.booktravel.utils.PathGetter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_user.*
import java.io.File


class UserFragment : Fragment(){

    var user = UserInfo()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater!!.inflate(R.layout.frag_user,container,false)
        return view
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
//        initData()//网络加载、数据请求操作
        initView()//适配器绑定等操作
        initClickListener()//监听器绑定操作
    }

    private fun initView() {
        if(BmobUser.getCurrentUser()!=null){
            user = BmobUser.getCurrentUser<UserInfo>(UserInfo::class.java)
            Glide.with(context).load(user.avatarUrl).into(riv_user_avatar)
            tv_username.text = user.username
        }
    }

    private fun initClickListener() {
        cv_tags.setOnClickListener { startActivity(Intent(activity, PickActivity::class.java)) }
        cv_avatar.setOnClickListener {
            val intent = Intent()
            /* 开启Pictures画面Type设定为image */
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            /* 取得相片后返回本画面 */
            startActivityForResult(intent, 1)
        }
        cv_mobile.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)
            dialog
                    .setTitle("请输入手机号码")
                    .setView(editText)
                    .setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which ->
                        val mobile = editText.text.toString()
                        val newUser = user
                        newUser.mobilePhoneNumber = mobile
                        newUser.update(user.objectId,object:UpdateListener(){
                            override fun done(p0: BmobException?) {
                                Toast.makeText(context,"联系方式更新成功", Toast.LENGTH_SHORT).show()
                            }
                        })
                    })
                    .setNegativeButton("取消", null)
                    .show()
        }
        cv_student_id.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)
            dialog
                    .setTitle("请输入学号")
                    .setView(editText)
                    .setPositiveButton("确定", { dialog, which ->
                        val studentId = editText.text.toString()
                        val newUser = user
                        newUser.studentId = studentId
                        newUser.update(user.objectId,object:UpdateListener(){
                            override fun done(p0: BmobException?) {
                                Toast.makeText(context,"学号更新成功", Toast.LENGTH_SHORT).show()
                            }
                        })
                    })
                    .setNegativeButton("取消", null)
                    .show()
        }
        cv_log_out.setOnClickListener {
            if(BmobUser.getCurrentUser()!=null){
                BmobUser.logOut()
                startActivity(Intent(activity,LoginActivity::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if(data!=null){
                val pic_path = data.data
                val file = BmobFile(File(PathGetter.getPath(activity,pic_path)))
                    file.uploadblock(object : UploadFileListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                user.avatarUrl = file.fileUrl
                                user.update(user.objectId,object:UpdateListener(){
                                    override fun done(p0: BmobException?) {
                                        Toast.makeText(context,"头像更新成功", Toast.LENGTH_SHORT).show()
                                        Glide.with(context).load(file.fileUrl).into(riv_avatar)
                                        Glide.with(context).load(file.fileUrl).into(riv_user_avatar)
                                    }
                                })
                            } else {
                                Toast.makeText(context,"上传文件失败：" + pic_path+ e.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onProgress(value: Int?) { }
                    })
            }
        }

    }
}
