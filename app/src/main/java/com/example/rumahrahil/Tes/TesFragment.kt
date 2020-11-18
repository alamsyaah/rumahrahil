package com.example.rumahrahil.Tes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rumahrahil.R
import kotlinx.android.synthetic.main.item_jenjang_tes.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TesFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_tes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cv_sd_tes.setOnClickListener {
            pilihKategori(SD)
        }
        cv_smp_tes.setOnClickListener {
            pilihKategori(SMP)
        }
        cv_sma_tes.setOnClickListener {
            pilihKategori(SMA)
        }
        cv_kedinasan_tes.setOnClickListener {
            pilihKategori(DINAS)
        }
    }

    private fun pilihKategori(jenjang: String) {
        val intent = Intent(activity, TesKategoriActivity::class.java)
//        val bundle: Bundle = Bundle()
        val choosenJenjang = jenjang
        when (choosenJenjang) {
            SD ->
//                bundle.putString(JENJANG, SD)
                intent.putExtra(JENJANG, SD)
            SMP ->
//                bundle.putString(JENJANG, SMP)
                intent.putExtra(JENJANG, SMP)
            SMA ->
//                bundle.putString(JENJANG, SMA)
                intent.putExtra(JENJANG, SMA)
            DINAS ->
//                bundle.putString(JENJANG, DINAS)
                intent.putExtra(JENJANG, DINAS)
        }
        startActivity(intent)
//        val transaction2 = requireActivity().supportFragmentManager.beginTransaction()
//        val frag2 = TesKategoriFragment()
//        val fragmentOld: Fragment? = requireActivity().supportFragmentManager.findFragmentByTag(TAG_TESFRAGMENT)
//        frag2.arguments = bundle

//        if (fragmentOld != null) {
//            frag2 = fragmentOld as TesKategoriFragment
//                    transaction2.hide(fragmentOld)
//
//        }

//        transaction2.add(R.id.nav_host_fragment, frag2)
//        transaction2.addToBackStack(null)
//        requireActivity().supportFragmentManager.popBackStackImmediate()
//        transaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction2.commit()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

        const val SD = "SD"
        const val SMP = "SMP"
        const val SMA = "SMA"
        const val DINAS = "DINAS"
        const val JENJANG = "JENJANG"
        const val TAG_TESFRAGMENT = "Tes Fragment"
    }
}