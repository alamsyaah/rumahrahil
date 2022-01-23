package com.example.rumahrahil.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rumahrahil.databinding.ListVideoBinding
import es.dmoral.toasty.Toasty

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.listVideoHolderView>() {

    private var listVideo = ArrayList<VideoEntity>()

    fun setListVideo(listVideo: ArrayList<VideoEntity>?) {
        if (listVideo == null) return
        this.listVideo.clear()
        this.listVideo.addAll(listVideo)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoAdapter.listVideoHolderView {
        val itemVideoBinding =
            ListVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listVideoHolderView(itemVideoBinding)
    }

    override fun onBindViewHolder(holder: VideoAdapter.listVideoHolderView, position: Int) {
        val video = listVideo[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    class listVideoHolderView(private val binding: ListVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videoItem: VideoEntity) {
            with(binding) {
                tvMapel.text = videoItem.mapel_name
                tvTime.text = videoItem.mapel_time

                Glide.with(itemView.context)
                    .load(videoItem.mapel_video)
                    .apply(RequestOptions().override(48, 48))
                    .into(imgVideo)

                itemView.setOnClickListener {
                    Toasty.info(
                        itemView.context,
                        "Maaf fitur ini masih dalam tahap pengembangan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

}