/*package com.example.cookwork.retrofit

import com.example.cookwork.room.CookDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtils {
    companion object{
        val baseURL = "http://kasimadalan.pe.hu/"
        fun getCookDao():CookDao{
            return Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).
                    build().create(CookDao::class.java)
        }
    }
}

 */