package com.example.rumahrahil.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rumahrahil.R
import com.example.rumahrahil.databinding.FragmentHomeBinding
import com.example.rumahrahil.ui.tes.TesKategoriActivity
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import es.dmoral.toasty.Toasty


class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var studentId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySharedPreferences = MySharedPreferences(requireActivity())

        val name = mySharedPreferences.getValue(Constants.SISWA_NAMA).toString()
        val photo = mySharedPreferences.getValue(Constants.SISWA_IMAGE).toString()
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!

        homeBinding.tvStudentName.text = name

        Glide.with(this.requireActivity())
            .load(photo)
            .apply(RequestOptions().override(100, 100))
            .into(homeBinding.imgStudent)

        homeBinding.buttonMulai.setOnClickListener {
            startActivity(Intent(this.requireActivity(), TesKategoriActivity::class.java))
        }

        homeBinding.btnSignUp.setOnClickListener {
            Toasty.info(
                this.requireActivity(),
                "Mohon maaf fitur ini masih dalam tahap pengembangan",
                Toast.LENGTH_SHORT
            ).show()
        }

        homeBinding.imgStudent.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_profile)
        }
    }
}