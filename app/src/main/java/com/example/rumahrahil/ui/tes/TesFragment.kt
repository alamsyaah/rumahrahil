package com.example.rumahrahil.ui.tes


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rumahrahil.databinding.FragmentTesBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import kotlinx.android.synthetic.main.item_jenjang_tes.*


class TesFragment : Fragment() {

    private lateinit var mTestBinding: FragmentTesBinding
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var studentId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mTestBinding = FragmentTesBinding.inflate(inflater, container, false)
        return mTestBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySharedPreferences = MySharedPreferences(this.requireActivity())
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!
        val jenjang = mySharedPreferences.getValue(Constants.SISWA_JENJANG_ID).toString()
        val tokenAuth = mySharedPreferences.getValue(Constants.TOKEN).toString()

        with(mTestBinding) {
            cvSdTes.visibility = View.GONE
            cvSmpTes.visibility = View.GONE
            cvSmaTes.visibility = View.GONE
            cvKedinasanTes.visibility = View.GONE
        }

        if (jenjang == "1") {
            with(mTestBinding) {
                cvSdTes.visibility = View.VISIBLE
                cvSdTes.setOnClickListener {
                    startActivity(
                        Intent(
                            this@TesFragment.requireActivity(),
                            TesKategoriActivity::class.java
                        )
                    )
                }
            }
        } else if (jenjang == "2") {
            with(mTestBinding) {
                cvSmpTes.visibility = View.VISIBLE
                cvSmpTes.setOnClickListener {
                    startActivity(
                        Intent(
                            this@TesFragment.requireActivity(),
                            TesKategoriActivity::class.java
                        )
                    )
                }
            }
        } else if (jenjang == "3") {
            with(mTestBinding) {
                cvSmaTes.visibility = View.VISIBLE
                cvSmaTes.setOnClickListener {
                    startActivity(
                        Intent(
                            this@TesFragment.requireActivity(),
                            TesKategoriActivity::class.java
                        )
                    )
                }
            }
        } else if (jenjang == "4") {
            with(mTestBinding) {
                cvKedinasanTes.visibility = View.VISIBLE
                cvKedinasanTes.setOnClickListener {
                    startActivity(
                        Intent(
                            this@TesFragment.requireActivity(),
                            TesKategoriActivity::class.java
                        )
                    )
                }
            }
        } else {
            Toast.makeText(
                this.requireActivity(),
                "Maaf Jenjang Tidak Ditemukan",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}