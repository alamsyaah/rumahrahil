package com.example.rumahrahil.tes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahrahil.R


lateinit var jenjang: String

class TesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)

        val fragment = TesFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()


    }


}