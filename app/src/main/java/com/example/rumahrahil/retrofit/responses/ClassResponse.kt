package com.example.rumahrahil.retrofit.responses

import com.example.rumahrahil.ui.tes.ClassEntity

data class ClassResponse(
    var status: Int,
    var message: String = "",
    var data: ArrayList<ClassEntity>
)