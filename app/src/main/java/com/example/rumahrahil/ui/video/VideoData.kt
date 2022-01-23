package com.example.rumahrahil.ui.video

import com.example.rumahrahil.R

object VideoData {
    private val mapelName = arrayOf(
        "Matematika Dasar",
        "PKN",
        "Fisika",
        "Bahasa Inggris",
        "Bahasa Indonesia",
        "Kimia",
        "Biologi",
        "Ekonomi",
        "Sosiologi",
        "Sejarah",
    )

    private val mapelTime = arrayOf(
        "10 Menit",
        "20 Menit",
        "10 Menit",
        "5 Meniit",
        "10 Menit",
        "7 Menit",
        "6 Menit",
        "5 Menit",
        "5 Menit",
        "10 Menit"
    )

    private val userVideo = intArrayOf(
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera,
        R.drawable.video_camera
    )

    val listData: ArrayList<VideoEntity>
        get() {
            val list = arrayListOf<VideoEntity>()
            for (position in mapelName.indices) {
                val listVideo = VideoEntity()

                listVideo.mapel_name = mapelName[position]
                listVideo.mapel_time = mapelTime[position]
                listVideo.mapel_video = userVideo[position]

                list.add(listVideo)
            }

            return list
        }
}