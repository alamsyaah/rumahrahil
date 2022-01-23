package com.example.rumahrahil.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MySharedPreferences(mContext: Context) {

    companion object {
        const val USER_PREF = "USER_PREF"
    }

    private val mSharedPreferences = mContext.getSharedPreferences(USER_PREF, 0)

    fun setValue(key: String, value: String) {
        val editor: SharedPreferences.Editor = mSharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setValueInteger(key: String, value: Int) {
        val editor: SharedPreferences.Editor = mSharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getValue(key: String): String? {
        return mSharedPreferences.getString(key, "")
    }

    fun getValueInteger(key: String): Int? {
        return mSharedPreferences.getInt(key, 0)
    }

    fun saveArrayList(key: String, list: String) {
        val editor: SharedPreferences.Editor = mSharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?): ArrayList<String?>? {
        val gson = Gson()
        val json: String? = mSharedPreferences.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(json, type)
    }

}