package com.example.rumahrahil.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rumahrahil.R
import com.example.rumahrahil.video.model.Video
import kotlinx.android.synthetic.main.item_vidcategory.view.*

class ListVideoAdapter() :
        RecyclerView.Adapter<ListVideoAdapter.MyViewHolder>() {

    var listVideo = arrayListOf<Video>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video) {
            with(itemView) {
                Glide.with(this)
                        .load(video.linkThumbnail)
                        .override(300, 150)
                        .into(iv_thumbnail_video)
                tv_title_carditem.text = video.nama
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun addToListAdapter(list: ArrayList<Video>) {
        listVideo.clear()
        listVideo.addAll(list)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListVideoAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_vidcategory, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(listVideo[position])
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClick(listVideo[holder.adapterPosition])
//        }
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listVideo[holder.adapterPosition])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listVideo.size

    interface OnItemClickCallback {
        fun onItemClick(data: Video)
    }
}