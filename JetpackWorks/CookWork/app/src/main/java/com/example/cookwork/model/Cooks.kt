package com.example.cookwork.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Cooks(

    val yemek_id:String?="",
    val yemek_adi:String?="",
    val yemek_resim_adi: String?="",
    val yemek_fiyat: Int?=0
)
