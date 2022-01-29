package com.example.rumahrahil.ui.tes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rumahrahil.R
import com.example.rumahrahil.databinding.ActivityTesKategoriBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import dev.shreyaspatil.MaterialDialog.MaterialDialog

class TesKategoriActivity : AppCompatActivity() {

    private lateinit var mTestCategoryBinding: ActivityTesKategoriBinding
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var studentId: String
    private lateinit var tesViewModel: TesViewModel
    private var mapMapel: MutableMap<String, String> = mutableMapOf()
    private var mapBab: MutableMap<String, String> = mutableMapOf()
    private var mapPaket: MutableMap<String, String> = mutableMapOf()
    private var mapWaktuPaket: MutableMap<String, String> = mutableMapOf()
    private var listClassName = ArrayList<String>()
    private var listJurusan = ArrayList<String>()
    private var BabName = ArrayList<String>()
    private var mapelName = ArrayList<String>()
    private var PaketName = ArrayList<String>()
    private var savedIdMapel: String = ""
    private var savedIdBab: String = ""
    private var savedIdPaket: String = ""
    private var savedTimePaket: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTestCategoryBinding = ActivityTesKategoriBinding.inflate(layoutInflater)
        setContentView(mTestCategoryBinding.root)


        mTestCategoryBinding.btnBacktohomeTes.setOnClickListener {
            super.onBackPressed()
        }

        mTestCategoryBinding.btnMulaiTes.setOnClickListener {
            val mDialog = MaterialDialog.Builder(this)
                .setTitle(getString(R.string.mulai_ujian))
                .setMessage(getString(R.string.message_dialog_teskategori))
                .setCancelable(true)
                .setPositiveButton(
                    getString(R.string.no),
                    R.drawable.dialog_close
                ) { dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                .setNegativeButton(
                    getString(R.string.yes), R.drawable.ic_check
                ) { dialogInterface, _ ->

                    if (validate()) {
                        val intent = Intent(this, FirstTesActivity::class.java).apply {
                            putExtra(Constants.PAKET_ID, savedIdPaket)
//                            putExtra(Constants.PAKET_TIME, savedTimePaket)
                        }

                        startActivity(intent)
                    }
                    dialogInterface.dismiss()
                }
                .build()
            mDialog.show()

        }

        mySharedPreferences = MySharedPreferences(this)
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!


        val idKelas = mySharedPreferences.getValue(Constants.SISWA_KELAS_ID).toString()
        val tokenAuth = mySharedPreferences.getValue(Constants.TOKEN).toString()


        tesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TesViewModel::class.java)


        getClass(idKelas, tokenAuth)
        getMapel(idKelas, tokenAuth)
    }

    private fun getClass(id_kelas: String, tokenAuth: String) {

        tesViewModel.getClass(id_kelas, tokenAuth)
        tesViewModel.mKelas.observe(this, { mKelas ->

            if (mKelas != null) {
                mKelas.forEach {
                    listClassName.add(it.nama_kelas)
                    listJurusan.add(it.jurusan)
                }

                mySharedPreferences.setValue(Constants.SISWA_JURUSAN, listJurusan.toString())
                mySharedPreferences.setValue(Constants.SISWA_KELAS, listClassName.toString())

                getGrade()
            }
        })

    }

    private fun getGrade() {
        val mGrade = mySharedPreferences.getValue(Constants.SISWA_JURUSAN)!!
        val mClass = mySharedPreferences.getValue(Constants.SISWA_KELAS)!!

        if (mGrade.contains("[")) {
            mGrade.replace("[", "")
            mTestCategoryBinding.tvGrade.text = mGrade
        }


        mTestCategoryBinding.tvGradeClass.text = mClass
    }


    private fun getMapel(id_kelas: String, tokenAuth: String) {

        tesViewModel.getMapel(id_kelas, tokenAuth)
        tesViewModel.mMapel.observe(this, { mMapel ->

            if (mMapel != null) {
                mMapel.forEach {
                    mapelName.add(it.nama_mapel)
                    mapMapel.put(it.nama_mapel, it.id_mapel)
                }

                val adapterMapel =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mapelName)

                mTestCategoryBinding.ddTemaTes.setAdapter(adapterMapel)


                mTestCategoryBinding.ddTemaTes.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        val getPositionMapel = parent?.getItemAtPosition(position)

                        for ((key, value) in mapMapel) {
                            if (getPositionMapel!! == key) {
                                savedIdMapel = value
                                Log.d("tes", "tes mapel : $savedIdMapel")
                                mySharedPreferences.setValue(Constants.MAPEL_ID, savedIdMapel)

//                                val idMapel =
//                                    mySharedPreferences.getValue(Constants.MAPEL_ID).toString()
                                val tokenAuth =
                                    mySharedPreferences.getValue(Constants.TOKEN).toString()

                                if (savedIdMapel != "") {
                                    getBab(savedIdMapel, tokenAuth)

                                } else {
                                    getBab(null.toString(), null.toString())
                                }
                            }
                        }
                    }
            }
        })
    }

    private fun getBab(id_mapel: String, tokenAuth: String) {

        mTestCategoryBinding.ddBabTes.text.clear()
        mTestCategoryBinding.ddPaketTes.text.clear()

        tesViewModel.getBab(id_mapel, tokenAuth)
        tesViewModel.mBab.observe(this, { mBab ->

            if (mBab != null) {
                BabName.clear()

                mBab.forEach {
                    BabName.add(it.nama_bab)
                    mapBab.put(it.nama_bab, it.id_bab)
                }

                val adapterBab = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    BabName
                )

                mTestCategoryBinding.ddBabTes.setAdapter(adapterBab)
                mTestCategoryBinding.ddBabTes.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        val getPositionBab = parent?.getItemAtPosition(position)
                        for ((key, value) in mapBab) {
                            if (getPositionBab!! == key) {
                                savedIdBab = value
                                Log.d("tes", "tes bab : $savedIdBab")
//                                mySharedPreferences.setValue(Constants.BAB_ID, savedIdBab)


//                                val idBab =
//                                    mySharedPreferences.getValue(Constants.BAB_ID).toString()
                                val tokenAuth =
                                    mySharedPreferences.getValue(Constants.TOKEN).toString()

                                if (savedIdBab != "") {
                                    getPaket(savedIdBab, tokenAuth)
                                } else {
                                    getPaket(null.toString(), null.toString())
                                }

                            }
                        }
                    }
            }
        })
    }

    private fun getPaket(id_bab: String, tokenAuth: String) {

        mTestCategoryBinding.ddPaketTes.text.clear()
        tesViewModel.getPaket(id_bab, tokenAuth)
        tesViewModel.mPaket.observe(this) { mPaket ->

            if (mPaket != null) {
                PaketName.clear()

                mPaket.forEach {
                    PaketName.add(it.nama_paket)
                    mapPaket.put(it.nama_paket, it.id_paket)
                    mapWaktuPaket.put(it.nama_paket, it.waktu)
                }

                val adapterPaket =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaketName)
                mTestCategoryBinding.ddPaketTes.setAdapter(adapterPaket)
                mTestCategoryBinding.ddPaketTes.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->

                        val getPositionPaket = parent?.getItemAtPosition(position)

                        for ((key, value) in mapPaket) {
                            if (getPositionPaket!! == key) {
                                savedIdPaket = value

                                Log.d("tes", "tes paket : $savedIdPaket")

                                mySharedPreferences.setValue(Constants.PAKET_ID, savedIdPaket)
                            }
                        }


//                        for ((key, value) in mapWaktuPaket) {
//                            if (getPositionPaket!! == key) {
//                                savedTimePaket = value
//                                Log.d("tes", "tes waktu paket : $savedTimePaket")
////                                mySharedPreferences.setValue(Constants.PAKET_TIME, timeSavedPaket)
//                            }
//                        }
                    }

            }

        }
    }


    private fun validate(): Boolean {
        if (mTestCategoryBinding.ddTemaTes.text.toString() == "") {
            mTestCategoryBinding.ddTemaTes.error = "Harap Pilih Mapel Terlebih Dahulu"
            mTestCategoryBinding.ddTemaTes.requestFocus()
            return false
        } else if (mTestCategoryBinding.ddBabTes.text.toString() == "") {
            mTestCategoryBinding.ddBabTes.error = "Harap Pilih Bab Terlebih Dahulu"
            mTestCategoryBinding.ddBabTes.requestFocus()
            return false
        } else if (mTestCategoryBinding.ddPaketTes.text.toString() == "") {
            mTestCategoryBinding.ddPaketTes.error = "Harap Pilih Paket Terlebih Dahulu"
            mTestCategoryBinding.ddPaketTes.requestFocus()
            return false
        }

        return true
    }
}
