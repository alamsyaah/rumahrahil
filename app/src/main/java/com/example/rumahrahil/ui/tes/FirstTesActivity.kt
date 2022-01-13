package com.example.rumahrahil.ui.tes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rumahrahil.databinding.ActivityFirstTesBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import es.dmoral.toasty.Toasty


class FirstTesActivity : AppCompatActivity() {

    private lateinit var mFirstTesBinding: ActivityFirstTesBinding
    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var studentId: String
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var mQuiz: ArrayList<QuestionEntity>
    private var soal = ArrayList<String>()
    private var optionA = ArrayList<String>()
    private var optionB = ArrayList<String>()
    private var optionC = ArrayList<String>()
    private var optionD = ArrayList<String>()
    private var optionE = ArrayList<String>()
    private var correctAnswer = ArrayList<String>()
    private var cthPaketId: String = "201"
    private var cthMapelId: String = "79"
    var mCurrentPosition: Int = 1
    var answerIndex: Int = 0
    var mScore: Int = 0
    lateinit var countdown_timer: CountDownTimer
    var time_in_milli_seconds = 0L
    val timeQuiz: String = "120"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirstTesBinding = ActivityFirstTesBinding.inflate(layoutInflater)
        setContentView(mFirstTesBinding.root)


        mySharedPreferences = MySharedPreferences(this)
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!
        val tokenAuth = mySharedPreferences.getValue(Constants.TOKEN).toString()

        questionViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(QuestionViewModel::class.java)

        mQuiz = arrayListOf<QuestionEntity>()

        getQuestion(cthMapelId, cthPaketId, tokenAuth)

        mFirstTesBinding.btnNext.setOnClickListener {
            if (mFirstTesBinding.rbFirstAnswer.isChecked || mFirstTesBinding.rbSecondAnswer.isChecked || mFirstTesBinding.rbThirdAnswer.isChecked || mFirstTesBinding.rbFourthAnswer.isChecked || mFirstTesBinding.rbFifthAnswer.isChecked) {
                mCurrentPosition++
//                answerIndex+1
                getQuestion(cthMapelId, cthPaketId, tokenAuth)
            } else {
                Toasty.error(this, "Jawaban Belum Anda Belum Pilih", Toast.LENGTH_SHORT).show()
            }
        }

        mFirstTesBinding.btnPrevious.setOnClickListener {
            mCurrentPosition--
//            answerIndex--
            getQuestion(cthPaketId, cthMapelId, tokenAuth)
        }

        time_in_milli_seconds = timeQuiz.toLong() * 60000L
        startTimer(time_in_milli_seconds)

    }

    private fun getQuestion(cthMapelId: String, cthPaketId: String, tokenAuth: String) {
        questionViewModel.getQuestion(cthMapelId, cthPaketId, tokenAuth)
        questionViewModel.mQuestion.observe(this, { mQuestion ->

            if (mQuestion != null) {
                mQuestion.forEach {
                    soal.add(it.soal)
                    optionA.add(it.option_a)
                    optionB.add(it.option_b)
                    optionC.add(it.option_c)
                    optionD.add(it.option_d)
                    optionE.add(it.option_e)
                    correctAnswer.add(it.jawaban_benar)
                }

                mFirstTesBinding.btnPrevious.visibility = View.GONE

                if ((mCurrentPosition - 1) == answerIndex) {
                    mFirstTesBinding.btnPrevious.visibility = View.INVISIBLE
                } else {
                    mFirstTesBinding.btnPrevious.visibility = View.VISIBLE
                }

//                val maxNumber = soal.size
                val question = soal[mCurrentPosition - 1]
                val firstOption = optionA[mCurrentPosition - 1]
                val secondOption = optionB[mCurrentPosition - 1]
                val thirdOption = optionC[mCurrentPosition - 1]
                val fourthOption = optionD[mCurrentPosition - 1]
                val fifthOption = optionE[mCurrentPosition - 1]
                val realOption = correctAnswer[answerIndex]

                if (mFirstTesBinding.rbFirstAnswer.isChecked) {

                    Log.d("KunciJawaban = ", realOption)
                    Log.d("Jawaban Anda = ", mFirstTesBinding.rbFirstAnswer.text as String)

                    if (mFirstTesBinding.rbFirstAnswer.text != realOption) {
                        Log.d("Gagal", "Salah")
                    } else {
                        Log.d("Sukses", "benar")
//                        mySharedPreferences.saveArrayList(Constants.optionA, mFirstTesBinding.rbFirstAnswer.text)
//                        mySharedPreferences.setValue(Constants.optionA, mFirstTesBinding.rbFirstAnswer.text)
                    }
                } else if (mFirstTesBinding.rbSecondAnswer.isChecked) {

                    Log.d("KunciJawaban = ", realOption)
                    Log.d("Jawaban Anda = ", mFirstTesBinding.rbSecondAnswer.text as String)

                    if (mFirstTesBinding.rbSecondAnswer.text != realOption) {
                        Log.d("Gagal", "Salah")
                    } else {
                        Log.d("Sukses", "benar")
//                        mySharedPreferences.setValue(Constants.optionA, mFirstTesBinding.rbSecondAnswer.text)
                    }
                } else if (mFirstTesBinding.rbThirdAnswer.isChecked) {

                    Log.d("KunciJawaban = ", realOption)
                    Log.d("Jawaban Anda = ", mFirstTesBinding.rbThirdAnswer.text as String)

                    if (mFirstTesBinding.rbThirdAnswer.text != realOption) {
                        Log.d("Gagal", "Salah")
                    } else {
                        Log.d("Sukses", "benar")
//                        mySharedPreferences.setValue(Constants.optionA, mFirstTesBinding.rbThirdAnswer.text)
                    }
                } else if (mFirstTesBinding.rbFourthAnswer.isChecked) {

                    Log.d("KunciJawaban = ", realOption)
                    Log.d("Jawaban Anda = ", mFirstTesBinding.rbFourthAnswer.text as String)

                    if (mFirstTesBinding.rbFourthAnswer.text != realOption) {
                        Log.d("Gagal", "Salah")
                    } else {
                        Log.d("Sukses", "benar")
//                        mySharedPreferences.setValue(Constants.optionA, mFirstTesBinding.rbFourthAnswer.text)
                    }
                } else if (mFirstTesBinding.rbFifthAnswer.isChecked) {

                    Log.d("KunciJawaban = ", realOption)
                    Log.d("Jawaban Anda = ", mFirstTesBinding.rbFifthAnswer.text as String)

                    if (mFirstTesBinding.rbFifthAnswer.text != realOption) {
                        Log.d("Gagal", "Salah")
                    } else {
                        Log.d("Sukses", "benar")
//                        mySharedPreferences.setValue(Constants.optionA, mFirstTesBinding.rbFifthAnswer.text)
                    }
                } else {
                    Log.d("ERROR", "Tidak Ada Jawaban yang dipilih")
                }

                with(mFirstTesBinding) {
                    tvFirstNumber.text = "$mCurrentPosition"
//                    tvLastNumber.text = maxNumber.toString()
                    tvQuestion.text = question
                    rbFirstAnswer.text = firstOption
                    rbSecondAnswer.text = secondOption
                    rbThirdAnswer.text = thirdOption
                    rbFourthAnswer.text = fourthOption
                    rbFifthAnswer.text = fifthOption
//                    radioGroupAnswer.clearCheck()
                }
            }
        })
    }

    private fun startTimer(time_in_second: Long) {
        countdown_timer = object : CountDownTimer(time_in_second, 1000) {
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }
        }
        countdown_timer.start()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60
        mFirstTesBinding.tvTime.text = "$minute:$seconds"
    }
}