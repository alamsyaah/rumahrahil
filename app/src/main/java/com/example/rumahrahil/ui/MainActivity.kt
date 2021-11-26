package com.example.rumahrahil.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.rumahrahil.R
import com.example.rumahrahil.utils.MySharedPreferences
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mySharedPreferences = MySharedPreferences(this)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        ExpandableBottomBarNavigationUI.setupWithNavController(expandable_bottom_bar, navController)
    }

}