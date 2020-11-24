package com.example.rumahrahil.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahrahil.MainActivity
import com.example.rumahrahil.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tv_gotosignup_login.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn_submit_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}