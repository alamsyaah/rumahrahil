package com.example.rumahrahil.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahrahil.R
import com.example.rumahrahil.video.model.Video
import kotlinx.android.synthetic.main.fragment_video.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VideoFragment : Fragment() {

    private var listVideo: ArrayList<Video> = arrayListOf()
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
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vid1 = Video("English", "https://www.youtube.com/watch?v=teMcwnplFv4", "https://img.youtube.com/vi/teMcwnplFv4/hqdefault.jpg")
        val vid2 = Video("Indonesia", "https://www.youtube.com/watch?v=m-IH3st1poE", "https://img.youtube.com/vi/m-IH3st1poE/hqdefault.jpg")
        val vid3 = Video("Elaina", "https://www.youtube.com/watch?v=3JmvuPaFbZw", "https://img.youtube.com/vi/3JmvuPaFbZw/hqdefault.jpg")
        rv_videos.layoutManager = LinearLayoutManager(activity)
        val listVideoAdapter = ListVideoAdapter()
        if (chip_cat_sd.isChecked) {
            chip_cat_sd.isActivated = false
            listVideo.clear()
            listVideo.add(vid1)
            listVideo.add(vid2)
            listVideo.add(vid3)
            listVideoAdapter.addToListAdapter(listVideo)
            listVideoAdapter.notifyDataSetChanged()
        }
        cg_category_video.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.chip_cat_sd) {
                chip_cat_sd.isActivated = false
                listVideo.clear()
                listVideo.add(vid1)
                listVideo.add(vid2)
                listVideo.add(vid3)
                listVideoAdapter.addToListAdapter(listVideo)
                listVideoAdapter.notifyDataSetChanged()
            } else if (checkedId == R.id.chip_cat_smp) {
                chip_cat_smp.isActivated = false
                listVideo.clear()
                listVideo.add(vid2)
                listVideoAdapter.addToListAdapter(listVideo)
                listVideoAdapter.notifyDataSetChanged()
            } else if (checkedId == R.id.chip_cat_sma) {
                chip_cat_sma.isActivated = false
                listVideo.clear()
                listVideo.add(vid3)
                listVideoAdapter.addToListAdapter(listVideo)
                listVideoAdapter.notifyDataSetChanged()
            } else if (checkedId == R.id.chip_cat_dinas) {
                chip_cat_dinas.isActivated = false
                listVideo.clear()
                listVideo.add(vid1)
                listVideoAdapter.addToListAdapter(listVideo)
                listVideoAdapter.notifyDataSetChanged()
            }
        }
        rv_videos.adapter = listVideoAdapter
        setOnClickItem(listVideoAdapter)
    }

    private fun setOnClickItem(listVideoAdapter: ListVideoAdapter) {
        listVideoAdapter.setOnItemClickCallback(object : ListVideoAdapter.OnItemClickCallback {
            override fun onItemClick(data: Video) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.linkVideo)))
            }
        })
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
                VideoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}