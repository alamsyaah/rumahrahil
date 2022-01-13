package com.example.rumahrahil.retrofit.responses

import com.example.rumahrahil.ui.tes.QuestionEntity
import java.util.*

data class QuestionResponse(
    var status: Int,
    var message: String = "",
    var data: ArrayList<QuestionEntity>
)