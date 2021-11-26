package com.example.rumahrahil.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rumahrahil.R
import com.example.rumahrahil.databinding.FragmentProfileBinding
import com.example.rumahrahil.ui.login.LoginActivity
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import dev.shreyaspatil.MaterialDialog.MaterialDialog

class ProfileFragment : Fragment() {

    private lateinit var mProfileFragmentBinding: FragmentProfileBinding
    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mProfileFragmentBinding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return mProfileFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreferences = MySharedPreferences(requireActivity())

        val name = mySharedPreferences.getValue(Constants.SISWA_NAMA).toString()
        val photo = mySharedPreferences.getValue(Constants.SISWA_IMAGE).toString()

        with(mProfileFragmentBinding) {
            tvUsername.text = name
            Glide.with(this@ProfileFragment.requireActivity())
                .load(photo)
                .apply(RequestOptions().override(100, 100))
                .into(imgStudent)
        }

        mProfileFragmentBinding.btnLogout.setOnClickListener {
            val mDialog = MaterialDialog.Builder(requireActivity() as Activity)
                .setTitle("Logout")
                .setMessage(getString(R.string.confirm_logout))
                .setCancelable(true)
                .setPositiveButton(
                    getString(R.string.no), R.drawable.dialog_close
                ) { dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                .setNegativeButton(
                    getString(R.string.yes), R.drawable.logout_icon
                ) { dialogInterface, _ ->
                    mySharedPreferences.setValue(Constants.SISWA, "")
                    mySharedPreferences.setValue(Constants.SISWA_ID, "")
                    mySharedPreferences.setValue(Constants.SISWA_NAMA, "")
                    mySharedPreferences.setValue(Constants.SISWA_USERNAME, "")
                    mySharedPreferences.setValue(Constants.SISWA_JENJANG_ID, "")
                    mySharedPreferences.setValue(Constants.SISWA_JURUSAN, "")
                    mySharedPreferences.setValue(Constants.SISWA_ALAMAT, "")
                    mySharedPreferences.setValue(Constants.SISWA_EMAIL, "")
                    mySharedPreferences.setValue(Constants.TOKEN, "")

                    startActivity(Intent(context, LoginActivity::class.java))
                    activity?.finish()
                    dialogInterface.dismiss()
                }
                .build()
            // Show Dialog
            mDialog.show()
        }

    }
}