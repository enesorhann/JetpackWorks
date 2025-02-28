package com.example.cookwork.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cookwork.model.Cooks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class CooksDaoRepo(application: Application) {

    val liveList = MutableLiveData<List<Cooks>>()
    val refCooks:DatabaseReference
    //val cookDao:CookDao
    //var db:DatabaseAccess

    init {
        val db = FirebaseDatabase.getInstance()
        refCooks = db.getReference("yemekler")
        //cookDao = ApiUtils.getCookDao()
        //db = DatabaseAccess.access(application)!!
        MutableLiveData<List<Cooks>>()
    }

    fun getLiveData(): MutableLiveData<List<Cooks>>{
        return  liveList
    }

    fun allCooks(){
        /* ROOM
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            liveList.value= db.cooksDao().allCooks()
        }
         */

        /* Retrofit
        cookDao.allCooks().enqueue(object : Callback<CooksResponse>{
            override fun onResponse(call: Call<CooksResponse>, response: Response<CooksResponse>) {
                val list = response.body()?.yemekler
                list?.let {
                    liveList.value = list!!
                }
            }

            override fun onFailure(call: Call<CooksResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
         */

        refCooks.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Cooks>()
                for (d in snapshot.children){
                    val cook = d.getValue(Cooks::class.java)
                    cook?.let {
                        list.add(it)
                    }
                }
                liveList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}