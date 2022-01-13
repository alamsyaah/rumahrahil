package com.example.rumahrahil.ui.tes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rumahrahil.retrofit.DataService
import com.example.rumahrahil.retrofit.RetrofitClient
import com.example.rumahrahil.retrofit.responses.QuestionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionViewModel : ViewModel() {

    private val _mQuestion = MutableLiveData<ArrayList<QuestionEntity>>()
    val mQuestion: LiveData<ArrayList<QuestionEntity>> = _mQuestion

    companion object {
        private const val TAG = "QuestionViewModel"
    }

    fun getQuestion(mapel_id: String, paket_id: String, tokenAuth: String) {
        val service = RetrofitClient().apiRequest().create(DataService::class.java)
        service.getSoal(mapel_id, paket_id, "Bearer $tokenAuth")
            .enqueue(object : Callback<QuestionResponse> {
                override fun onResponse(
                    call: Call<QuestionResponse>,
                    response: Response<QuestionResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.status == 200) {
                            Log.e(TAG, response.message())
                            _mQuestion.value = response.body()!!.data
                        }
                    } else {
                        Log.e(TAG, response.message())
                    }
                }

                override fun onFailure(call: Call<QuestionResponse>, t: Throwable) {
                    Log.e(TAG, "${t.message}")
                }
            })
    }
}