package com.example.rumahrahil.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahrahil.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_submit_register.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_btologin_register.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}