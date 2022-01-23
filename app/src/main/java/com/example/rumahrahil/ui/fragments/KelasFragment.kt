package com.example.rumahrahil.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rumahrahil.R
import com.example.rumahrahil.databinding.FragmentKelasBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import es.dmoral.toasty.Toasty


class KelasFragment : Fragment() {

    private lateinit var mKelasBinding: FragmentKelasBinding
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var studentId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mKelasBinding = FragmentKelasBinding.inflate(inflater, container, false)
        return mKelasBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySharedPreferences = MySharedPreferences(requireActivity())

        val name = mySharedPreferences.getValue(Constants.SISWA_NAMA).toString()
        val photo = mySharedPreferences.getValue(Constants.SISWA_IMAGE).toString()
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!

        mKelasBinding.tvUsername.text = name

        mKelasBinding.imgStudent.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_kelas_to_navigation_profile)
        }

//        mKelasBinding.firstCardview.setOnClickListener {
//            Toasty.info(this.requireActivity(), "Mohon maaf fitur ini masih dalam tahap pengembangan", Toast.LENGTH_SHORT).show()
//        }
//
//        mKelasBinding.secondCardview.setOnClickListener {
//            Toasty.info(this.requireActivity(), "Mohon maaf fitur ini masih dalam tahap pengembangan", Toast.LENGTH_SHORT).show()
//        }
//
//        mKelasBinding.thirdCardview.setOnClickListener {
//            Toasty.info(this.requireActivity(), "Mohon maaf fitur ini masih dalam tahap pengembangan", Toast.LENGTH_SHORT).show()
//        }
//
//        mKelasBinding.fourthCardview.setOnClickListener {
//            Toasty.info(this.requireActivity(), "Mohon maaf fitur ini masih dalam tahap pengembangan", Toast.LENGTH_SHORT).show()
//        }

        mKelasBinding.tvFirstViewAll.setOnClickListener {
            Toasty.info(
                this.requireActivity(),
                "Mohon maaf fitur ini masih dalam tahap pengembangan",
                Toast.LENGTH_SHORT
            ).show()
        }

        mKelasBinding.tvSecondViewAll.setOnClickListener {
            Toasty.info(
                this.requireActivity(),
                "Mohon maaf fitur ini masih dalam tahap pengembangan",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

}