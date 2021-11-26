package com.example.rumahrahil.retrofit.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class Data(

    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("token")
    val token: String? = null
)

data class User(

    @field:SerializedName("sekolah")
    val sekolah: String? = null,

    @field:SerializedName("id_siswa_profile")
    val idSiswaProfile: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("jenjang_id")
    val jenjangId: String? = null,

    @field:SerializedName("jurusan")
    val jurusan: String? = null,

    @field:SerializedName("kelas_id")
    val kelasId: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null
)
