package com.example.rumahrahil.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rumahrahil.databinding.FragmentVideoBinding
import com.example.rumahrahil.utils.Constants
import com.example.rumahrahil.utils.MySharedPreferences
import es.dmoral.toasty.Toasty


class VideoFragment : Fragment() {

    private lateinit var mVideoBinding: FragmentVideoBinding
    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var studentId: String
//    private lateinit var mVideo: ArrayList<VideoEntity>
//    private lateinit var mVideoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mVideoBinding = FragmentVideoBinding.inflate(inflater, container, false)
        return mVideoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mVideoAdapter = VideoAdapter()
//        mVideo = arrayListOf()

        mySharedPreferences = MySharedPreferences(this.requireActivity())
        studentId = mySharedPreferences.getValue(Constants.SISWA_ID)!!
        val jenjang = mySharedPreferences.getValue(Constants.SISWA_JENJANG_ID).toString()

        if (jenjang == "1") {
            mVideoBinding.tvGrade.text = "SD"
        } else if (jenjang == "2") {
            mVideoBinding.tvGrade.text = "SMP"
        } else if (jenjang == "3") {
            mVideoBinding.tvGrade.text = "SMA"
        } else if (jenjang == "4") {
            mVideoBinding.tvGrade.text = "Kedinasan"
        } else {
            Toasty.error(
                this.requireActivity(),
                "Maaf Jenjang Tidak Ditemukan",
                Toast.LENGTH_SHORT
            ).show()
        }

//        showList()
    }

//    private fun showList(){
//        mVideo.addAll(VideoData.listData)
//        mVideoAdapter.setListVideo(mVideo)
//
//        with(mVideoBinding.rvVideo){
//            layoutManager = LinearLayoutManager(this@VideoFragment.requireActivity())
//            setHasFixedSize(true)
//            adapter = mVideoAdapter
//
//        }
//    }


}