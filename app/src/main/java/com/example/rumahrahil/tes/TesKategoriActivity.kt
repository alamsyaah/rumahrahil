package com.example.rumahrahil.tes

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahrahil.R
import com.example.rumahrahil.tes.TesFragment.Companion.JENJANG
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tes_kategori.*

class TesKategoriActivity : AppCompatActivity() {

    lateinit var adapterKelas: ArrayAdapter<String>
    lateinit var adapterTema: ArrayAdapter<String>
    lateinit var adapterBab: ArrayAdapter<String>
    lateinit var adapterPaket: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes_kategori)

        val choosenJenjang = intent.getStringExtra(JENJANG).toString()

        btn_backtohome_tes.setOnClickListener {
            super.onBackPressed()
        }

        btn_mulai_tes.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.mulai_ujian))
                    .setMessage(getString(R.string.message_dialog_teskategori))
                    .setPositiveButton(getString(R.string.positive_dialog)) { dialog, which ->
                        Snackbar.make(it, "Tes dimulai", Snackbar.LENGTH_SHORT).show()
                        startActivity(Intent(this, DoTestActivity::class.java))
                    }
                    .setNegativeButton(getString(R.string.negative_dialog)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
        }

        placingDropdown(choosenJenjang.toString())
        dd_kelas_tes.setAdapter(adapterKelas)
        dd_tema_tes.setAdapter(adapterTema)
        dd_bab_tes.setAdapter(adapterBab)
        dd_paket_tes.setAdapter(adapterPaket)
    }

    private fun placingDropdown(choosenJenjang: String) {
        val kelasSD = listOf("1(Satu)", "2(Dua)", "3(Tiga)", "4(Empat)", "5(Lima)", "6(Enam)", "Ujian Sekolah")
        val kelasSMP = listOf("7(Tujuh)", "8(Delapan)", "9(Sembilan)")
        val kelasSMA = listOf("10(Sepuluh)", "11(Sebelas)", "12(Dua Belas)")
        val kelasDinas = listOf("Statistika", "Akuntansi")
        val kelasSbmptn = listOf("Saintek", "Soshum")
        val temaSD = listOf("Matematika", "Bahasa Indonesia", "Bahasa Inggris")
        val temaSMP = listOf("Biologi", "Fisika", "Bahasa Inggris")
        val temaSMA = listOf("Kimia", "Astronomi")
        val temaDinas = listOf("Tema1", "Tema2")
        val temaSbmptn = listOf("Tema1", "Tema2")
        val babSD = listOf("1(Satu)", "2(Dua)")
        val babSMP = listOf("3(Tiga)", "4(Empat)")
        val babSMA = listOf("5(Lima)", "6(Enam)")
        val babDinas = listOf("bab1", "bab2")
        val babSbmptn = listOf("bab1", "bab2")
        val paket = listOf("A", "B")
        when (choosenJenjang) {
            "SD" ->
                adapterKelas = ArrayAdapter(this, R.layout.item_kategori_dropdown, kelasSD)
            "SMP" ->
                adapterKelas = ArrayAdapter(this, R.layout.item_kategori_dropdown, kelasSMP)
            "SMA" ->
                adapterKelas = ArrayAdapter(this, R.layout.item_kategori_dropdown, kelasSMA)
            "DINAS" ->
                adapterKelas = ArrayAdapter(this, R.layout.item_kategori_dropdown, kelasDinas)
            "SBMPTN" ->
                adapterKelas = ArrayAdapter(this, R.layout.item_kategori_dropdown, kelasSbmptn)
        }
        when (choosenJenjang) {
            "SD" ->
                adapterTema = ArrayAdapter(this, R.layout.item_kategori_dropdown, temaSD)
            "SMP" ->
                adapterTema = ArrayAdapter(this, R.layout.item_kategori_dropdown, temaSMP)
            "SMA" ->
                adapterTema = ArrayAdapter(this, R.layout.item_kategori_dropdown, temaSMA)
            "DINAS" ->
                adapterTema = ArrayAdapter(this, R.layout.item_kategori_dropdown, temaDinas)
            "SBMPTN" ->
                adapterTema = ArrayAdapter(this, R.layout.item_kategori_dropdown, temaSbmptn)
        }
        when (choosenJenjang) {
            "SD" ->
                adapterBab = ArrayAdapter(this, R.layout.item_kategori_dropdown, babSD)
            "SMP" ->
                adapterBab = ArrayAdapter(this, R.layout.item_kategori_dropdown, babSMP)
            "SMA" ->
                adapterBab = ArrayAdapter(this, R.layout.item_kategori_dropdown, babSMA)
            "DINAS" ->
                adapterBab = ArrayAdapter(this, R.layout.item_kategori_dropdown, babDinas)
            "SBMPTN" ->
                adapterBab = ArrayAdapter(this, R.layout.item_kategori_dropdown, babSbmptn)
        }
        adapterPaket = ArrayAdapter(this, R.layout.item_kategori_dropdown, paket)
    }
}