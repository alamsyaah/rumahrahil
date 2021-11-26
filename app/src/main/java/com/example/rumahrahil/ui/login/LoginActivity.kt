package com.example.rumahrahil.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahrahil.databinding.ActivityLoginBinding
import com.example.rumahrahil.retrofit.DataService
import com.example.rumahrahil.retrofit.RetrofitClient
import com.example.rumahrahil.retrofit.responses.LoginResponse
import com.example.rumahrahil.ui.MainActivity
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var mLoginBinding: ActivityLoginBinding
    private lateinit var myPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mLoginBinding.root)

        mLoginBinding.btnSubmitLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        myPreferences = MySharedPreferences(this)

        if (myPreferences.getValue(Constants.SISWA).equals(Constants.LOGIN)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        mLoginBinding.btnSubmitLogin.setOnClickListener {
            if (validate()) {
                val mUsername = mLoginBinding.etUsername.text.toString()
                val mPassword = mLoginBinding.etPassword.text.toString()

                loginProcess(mUsername, mPassword)

            }
        }
    }

    private fun validate(): Boolean {
        if (mLoginBinding.etUsername.text.toString() == "") {
            mLoginBinding.etUsername.error = "Harap Isi Username Terlebih Dahulu"
            mLoginBinding.etUsername.requestFocus()
            return false
        } else if (mLoginBinding.etPassword.text.toString() == "") {
            mLoginBinding.etPassword.error = "Harap Isi Password Terlebih Dahulu"
            mLoginBinding.etPassword.requestFocus()
            return false
        }

        return true
    }

    private fun loginProcess(username: String, password: String) {
        val service = RetrofitClient().apiRequest().create(DataService::class.java)
        service.login(username, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    when (response.body()!!.status) {
                        200 -> {
                            myPreferences.setValue(Constants.SISWA, Constants.LOGIN)
                            myPreferences.setValue(
                                Constants.SISWA_ID,
                                response.body()!!.data!!.user!!.idSiswaProfile!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_NAMA,
                                response.body()!!.data!!.user!!.nama!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_USERNAME,
                                response.body()!!.data!!.user!!.username!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_JENJANG_ID,
                                response.body()!!.data!!.user!!.jenjangId!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_KELAS_ID,
                                response.body()!!.data!!.user!!.kelasId!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_JURUSAN,
                                response.body()!!.data!!.user!!.jurusan!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_ALAMAT,
                                response.body()!!.data!!.user!!.alamat!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_EMAIL,
                                response.body()!!.data!!.user!!.email!!
                            )
                            myPreferences.setValue(
                                Constants.SISWA_IMAGE,
                                response.body()!!.data!!.user!!.image!!
                            )
                            myPreferences.setValue(
                                Constants.TOKEN,
                                response.body()!!.data!!.token!!
                            )
                            Toast.makeText(this@LoginActivity, "Berhasil login", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }

                        404 -> {
                            Toast.makeText(
                                this@LoginActivity,
                                "Maaf Username/Password yang Anda Masukkan Salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

//                        "404" ->{
//                            Toast.makeText(this@LoginActivity, "Maaf Akun Anda Tidak Ditemukan", Toast.LENGTH_SHORT).show()
//                        }
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Silahkan Coba Lagi", Toast.LENGTH_SHORT).show()
            }

        })
    }
}