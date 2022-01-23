package com.example.rumahrahil.ui.tes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rumahrahil.retrofit.DataService
import com.example.rumahrahil.retrofit.RetrofitClient
import com.example.rumahrahil.retrofit.responses.BabResponse
import com.example.rumahrahil.retrofit.responses.ClassResponse
import com.example.rumahrahil.retrofit.responses.MapelResponse
import com.example.rumahrahil.retrofit.responses.PaketResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TesViewModel : ViewModel() {
    private val _mKelas = MutableLiveData<ArrayList<ClassEntity>>()
    val mKelas: LiveData<ArrayList<ClassEntity>> = _mKelas

    private val _mBab = MutableLiveData<ArrayList<BabEntity>>()
    val mBab: LiveData<ArrayList<BabEntity>> = _mBab

    private val _mMapel = MutableLiveData<ArrayList<MapelEntity>>()
    val mMapel: LiveData<ArrayList<MapelEntity>> = _mMapel

    private val _mPaket = MutableLiveData<ArrayList<PaketEntity>>()
    val mPaket: LiveData<ArrayList<PaketEntity>> = _mPaket

    companion object {
        private const val TAG = "TesViewModel"
    }

    fun getClass(id_kelas: String, tokenAuth: String) {
        val service = RetrofitClient().apiRequest().create(DataService::class.java)
        service.getKelas(id_kelas, "Bearer $tokenAuth").enqueue(object : Callback<ClassResponse> {
            override fun onResponse(call: Call<ClassResponse>, response: Response<ClassResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e(TAG, response.message())
                        _mKelas.value = response.body()!!.data
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<ClassResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }

        })
    }

    fun getBab(mapel_id: String, tokenAuth: String) {
        val service = RetrofitClient().apiRequest().create(DataService::class.java)
        service.getBab(mapel_id, "Bearer $tokenAuth").enqueue(object : Callback<BabResponse> {
            override fun onResponse(call: Call<BabResponse>, response: Response<BabResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e(TAG, response.message())
                        _mBab.value = response.body()!!.data
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<BabResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }
        })
    }

    fun getMapel(id: String, tokenAuth: String) {
        val service = RetrofitClient().apiRequest().create(DataService::class.java)
        service.getMapel(id, "Bearer $tokenAuth").enqueue(object : Callback<MapelResponse> {
            override fun onResponse(call: Call<MapelResponse>, response: Response<MapelResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e(TAG, response.message())
                        _mMapel.value = response.body()!!.data
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<MapelResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }

        })
    }

    fun getPaket(id: String, tokenAuth: String) {
        val service = RetrofitClient().apiRequest().create(DataService::class.java)
        service.getPaket(id, "Bearer $tokenAuth").enqueue(object : Callback<PaketResponse> {
            override fun onResponse(call: Call<PaketResponse>, response: Response<PaketResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.e(TAG, response.message())
                        _mPaket.value = response.body()!!.data
                    }
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<PaketResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }
        })
    }
}