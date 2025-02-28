/*package com.example.contactwork.retrofit

import com.example.contactwork.room.PersonDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtils {

    companion object{
        val base_url = "http://kasimadalan.pe.hu/"
        fun getPersonDao():PersonDao{
            return Retrofit.Builder().
            baseUrl(base_url).
            addConverterFactory(GsonConverterFactory.create()).
            build().
            create(PersonDao::class.java)
        }
    }
}

 */