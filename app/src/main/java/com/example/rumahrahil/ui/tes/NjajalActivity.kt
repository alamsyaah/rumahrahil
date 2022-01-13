package com.example.rumahrahil.ui.tes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahrahil.databinding.ActivityNjajalBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences

class NjajalActivity : AppCompatActivity() {

    private lateinit var mNjajalBinding: ActivityNjajalBinding
    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNjajalBinding = ActivityNjajalBinding.inflate(layoutInflater)
        setContentView(mNjajalBinding.root)

        mySharedPreferences = MySharedPreferences(this)
        mNjajalBinding.tvMapel.text = mySharedPreferences.getValue(Constants.MAPEL_ID)

    }
}