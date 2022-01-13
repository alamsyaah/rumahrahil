package com.example.rumahrahil.retrofit.responses

import com.example.rumahrahil.ui.tes.MapelEntity

data class MapelResponse(
    var status: Int,
    var message: String = "",
    var data: ArrayList<MapelEntity>
)