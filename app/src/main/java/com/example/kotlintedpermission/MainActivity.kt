package com.example.kotlintedpermission

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idButton1.setOnClickListener {
            val pl = object : PermissionListener{
                override fun onPermissionGranted() {
                    val uri1 = Uri.parse("tel:010-1111-2222")
                    val intent1 = Intent(Intent.ACTION_CALL, uri1)
                    startActivity(intent1)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "HelloWorld", Toast.LENGTH_SHORT).show()
                }
            }

            TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setDeniedMessage("권한을 거부하면 통화가 불가능합니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()

        }
    }
}