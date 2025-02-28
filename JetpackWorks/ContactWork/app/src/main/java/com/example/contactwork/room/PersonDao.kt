/*package com.example.contactwork.room


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//Room
/*
@Dao
interface PersonDao {

    @Query("SELECT *FROM kisiler")
    suspend fun allPersonels():List<Person>

    @Insert
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT *FROM kisiler WHERE kisi_ad LIKE '%'|| :searchedPerson || '%' ")
    suspend fun searchPerson(searchedPerson: String):List<Person>
}

 */

interface PersonDao {

    @GET("kisiler/tum_kisiler.php")
    fun allPersonels():Call<PersonResponse>

    @POST("kisiler/insert_kisiler.php")
    @FormUrlEncoded
    fun insertPerson(
        @Field("kisi_ad") kisi_ad:String,
        @Field("kisi_tel") kisi_tel:String,
    ):Call<CRUDResponse>

    @POST("kisiler/update_kisiler.php")
    @FormUrlEncoded
    fun updatePerson(@Field("kisi_id") kisi_id:Int,
                     @Field("kisi_ad") kisi_ad:String,
                     @Field("kisi_tel") kisi_tel:String
                     ):Call<CRUDResponse>

    @POST("kisiler/delete_kisiler.php")
    @FormUrlEncoded
    fun deletePerson(@Field("kisi_id") kisi_id:Int):Call<CRUDResponse>

    @POST("kisiler/tum_kisiler_arama.php")
    @FormUrlEncoded
    fun searchPerson(@Field("kisi_ad") kisi_ad:String):Call<PersonResponse>
}

 */