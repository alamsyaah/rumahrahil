package com.example.rumahrahil.retrofit.responses

import com.example.rumahrahil.ui.tes.BabEntity

data class BabResponse(
    var status: Int,
    var message: String = "",
    var data: ArrayList<BabEntity>
)