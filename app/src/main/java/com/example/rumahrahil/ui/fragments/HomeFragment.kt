package com.example.rumahrahil.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rumahrahil.databinding.FragmentHomeBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences


class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mySharedPreferences: MySharedPreferences

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

        homeBinding.tvStudentName.text = name

        Glide.with(this.requireActivity())
            .load(photo)
            .apply(RequestOptions().override(100, 100))
            .into(homeBinding.imgStudent)


    }
}