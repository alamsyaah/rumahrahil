package com.example.rumahrahil.ui.tes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rumahrahil.R
import com.example.rumahrahil.databinding.ActivityTesKategoriBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tes_kategori.*

class TesKategoriActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var mTestCategoryBinding: ActivityTesKategoriBinding
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var studentId: String
    private lateinit var tesViewModel: TesViewModel
    private var mapMapel: MutableMap<String, String> = mutableMapOf()
    private var listIdClass = ArrayList<String>()
    private var listIdGrade = ArrayList<String>()
    private var listClassName = ArrayList<String>()
    private var listJurusan = ArrayList<String>()
    private var IdBab = ArrayList<String>()
    private var IdMapel = ArrayList<String>()
    private var BabName = ArrayList<String>()
    private var Semester = ArrayList<String>()
    private var listIdPaket = ArrayList<String>()
    private var mapelName = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTestCategoryBinding = ActivityTesKategoriBinding.inflate(layoutInflater)
        setContentView(mTestCategoryBinding.root)


        btn_backtohome_tes.setOnClickListener {
            super.onBackPressed()
        }

        btn_mulai_tes.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.mulai_ujian))
                .setMessage(getString(R.string.message_dialog_teskategori))
                .setPositiveButton(getString(R.string.positive_dialog)) { dialog, which ->
                    Snackbar.make(it, "Tes dimulai", Snackbar.LENGTH_SHORT).show()
                    startActivity(Intent(this, FirstTesActivity::class.java))
//                    startActivity(Intent(this, NjajalActivity::class.java))
                }
                .setNegativeButton(getString(R.string.negative_dialog)) { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }

        mySharedPreferences = MySharedPreferences(this)
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!
        val idMapel = mySharedPreferences.getValue(Constants.MAPEL_ID)!!
        val idKelas = mySharedPreferences.getValue(Constants.SISWA_KELAS_ID).toString()
        val tokenAuth = mySharedPreferences.getValue(Constants.TOKEN).toString()

        tesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TesViewModel::class.java)

        getClass(idKelas, tokenAuth)
        getMapel(idKelas, tokenAuth)
        getBab(idMapel, tokenAuth)

    }

    private fun getClass(id_kelas: String, tokenAuth: String) {

        tesViewModel.getClass(id_kelas, tokenAuth)
        tesViewModel.mKelas.observe(this, { mKelas ->

            if (mKelas != null) {
                mKelas.forEach {
                    listIdClass.add(it.id_kelas)
                    listIdGrade.add(it.jenjang_id)
                    listClassName.add(it.nama_kelas)
                    listJurusan.add(it.jurusan)
                }

                mTestCategoryBinding.ddKelasTes.onItemSelectedListener = this

                val adapterKelas = ArrayAdapter(
                    this@TesKategoriActivity, android.R.layout.simple_spinner_dropdown_item,
                    listClassName
                )
                mTestCategoryBinding.ddKelasTes.setAdapter(adapterKelas)
            }
        })

    }

    private fun getBab(id_mapel: String, tokenAuth: String) {

        tesViewModel.getBab(id_mapel, tokenAuth)
        tesViewModel.mBab.observe(this, { mBab ->

            if (mBab != null) {
                mBab.forEach {
                    IdBab.add(it.id_bab)
                    IdMapel.add(it.mapel_id)
                    BabName.add(it.nama_bab)
                    Semester.add(it.semester)
                }

                mTestCategoryBinding.ddBabTes.onItemSelectedListener =
                    this@TesKategoriActivity

                val adapterBab = ArrayAdapter(
                    this@TesKategoriActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    BabName
                )
                mTestCategoryBinding.ddBabTes.setAdapter(adapterBab)
            }
        })
    }

    private fun getMapel(id_kelas: String, tokenAuth: String) {

        tesViewModel.getMapel(id_kelas, tokenAuth)
        tesViewModel.mMapel.observe(this, { mMapel ->

            if (mMapel != null) {
                mMapel.forEach {
                    IdMapel.add(it.id_mapel)
                    listIdGrade.add(it.kelas_id)
                    listIdPaket.add(it.paket_id)
                    mapelName.add(it.nama_mapel)
                    mapMapel.put(listClassName.toString(), listIdClass.toString())
                }

                mTestCategoryBinding.ddTemaTes.onItemSelectedListener = this
                val adapterMapel =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mapelName)

                mTestCategoryBinding.ddTemaTes.setAdapter(adapterMapel)
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            mTestCategoryBinding.ddTemaTes.id -> {

                for ((key, value) in mapMapel) {
                    val saveMapel = parent.getItemAtPosition(position).toString()
                    if (saveMapel == key) {
                        Log.d("Tes :", value)
//                        mySharedPreferences.setValue(Constants.MAPEL_ID, value)
                    }
                }
            }

            mTestCategoryBinding.ddBabTes.id -> {
                val saveBab = parent.getItemAtPosition(position).toString()
                mySharedPreferences.setValue(Constants.BAB_ID, saveBab)
            }

//            mTestCategoryBinding.ddPaketTes.id -> {
//
//            }
        }


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
