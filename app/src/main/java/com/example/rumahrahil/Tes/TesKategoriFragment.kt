package com.example.rumahrahil.Tes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.rumahrahil.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tes_kategori.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TesKategoriFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TesKategoriFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var adapterKelas: ArrayAdapter<String>
    lateinit var adapterTema: ArrayAdapter<String>
    lateinit var adapterBab: ArrayAdapter<String>
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tes_kategori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        var choosenJenjang = ""
        if (bundle != null) {
            choosenJenjang = bundle.getString(JENJANG)!!
        }

        btn_mulai_tes.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.mulai_ujian))
                    .setMessage(getString(R.string.message_dialog_teskategori))
                    .setPositiveButton(getString(R.string.positive_dialog)) { dialog, which ->
                        Snackbar.make(it, "Tes dimulai", Snackbar.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(getString(R.string.negative_dialog)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
        }

        placingDropdown(choosenJenjang)
        dd_kelas_tes.setAdapter(adapterKelas)
        dd_tema_tes.setAdapter(adapterTema)
        dd_bab_tes.setAdapter(adapterBab)
    }

    private fun placingDropdown(choosenJenjang: String) {
        val kelasSD = listOf("1(Satu)", "2(Dua)", "3(Tiga)", "4(Empat)", "5(Lima)", "6(Enam)")
        val kelasSMP = listOf("7(Tujuh)", "8(Delapan)", "9(Sembilan)")
        val kelasSMA = listOf("10(Sepuluh)", "11(Sebelas)", "12(Dua Belas)")
        val kelasDinas = listOf("Statistika", "Akuntansi")
        val temaSD = listOf("Matematika", "Bahasa Indonesia", "Bahasa Inggris")
        val temaSMP = listOf("Biologi", "Fisika", "Bahasa Inggris")
        val temaSMA = listOf("Kimia", "Astronomi")
        val temaDinas = listOf("Tema1", "Tema2")
        val babSD = listOf("1(Satu)", "2(Dua)")
        val babSMP = listOf("3(Tiga)", "4(Empat)")
        val babSMA = listOf("5(Lima)", "6(Enam)")
        val babDinas = listOf("bab1", "bab2")

        when (choosenJenjang) {
            "SD" ->
                adapterKelas = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, kelasSD)

            "SMP" ->
                adapterKelas = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, kelasSMP)

            "SMA" ->
                adapterKelas = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, kelasSMA)
            "DINAS" ->
                adapterKelas = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, kelasDinas)
        }
        when (choosenJenjang) {
            "SD" ->
                adapterTema = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, temaSD)

            "SMP" ->
                adapterTema = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, temaSMP)

            "SMA" ->
                adapterTema = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, temaSMA)
            "DINAS" ->
                adapterTema = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, temaDinas)
        }
        when (choosenJenjang) {
            "SD" ->
                adapterBab = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, babSD)

            "SMP" ->
                adapterBab = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, babSMP)

            "SMA" ->
                adapterBab = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, babSMA)
            "DINAS" ->
                adapterBab = ArrayAdapter(activity!!.applicationContext, R.layout.item_kategori_dropdown, babDinas)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TesKategoriFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TesKategoriFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

        const val JENJANG = "JENJANG"
    }
}