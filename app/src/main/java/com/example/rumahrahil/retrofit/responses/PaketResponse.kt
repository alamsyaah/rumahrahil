package com.example.rumahrahil.retrofit.responses

import com.example.rumahrahil.ui.tes.PaketEntity

data class PaketResponse(
    var status: Int,
    var message: String = "",
    var data: ArrayList<PaketEntity>
)