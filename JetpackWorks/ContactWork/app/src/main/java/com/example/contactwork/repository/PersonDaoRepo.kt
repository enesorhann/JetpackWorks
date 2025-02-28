package com.example.contactwork.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.contactwork.model.Persons
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PersonDaoRepo(application: Application) {
    val personList = MutableLiveData<List<Persons>>()
    //val db:DatabaseAccess
    //val personDao:PersonDao
    val refPersons:DatabaseReference
    init {
        val db = FirebaseDatabase.getInstance()
        refPersons = db.getReference("persons")
        //personDao = ApiUtils.getPersonDao()
      //  db = DatabaseAccess.access(application)!!
        MutableLiveData<List<Persons>>()
    }

    fun getLiveData():MutableLiveData<List<Persons>>{
        return personList
    }

    fun searchPerson(searchedPerson:String){
        /* Room
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            personList.value = db.personDao().searchPerson(searchedPerson)
        }
         */

       /* RETROFIT
        personDao.searchPerson(searchedPerson).enqueue(object : Callback<PersonResponse>{
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {
                val list = response.body()?.persons
                list?.let {
                    personList.value = list!!
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
            }
        })
        */

        refPersons.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Persons>()
                for (d in snapshot.children){
                    val person = d.getValue(Persons::class.java)
                    person?.let {
                        person.person_id = d.key
                        if (person.person_name!!.lowercase().contains(searchedPerson.lowercase())){
                            list.add(person)
                        }

                    }
                }
                personList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
    fun deletePerson(personID:String){
       /* val job:Job = CoroutineScope(Dispatchers.Main).launch {
            val deletePerson = Person(personID,"","")
            db.personDao().deletePerson(deletePerson)
            allPeople()
        }
        */
/* RETROFIT
personDao.deletePerson(personID).enqueue(object : Callback<CRUDResponse>{
    override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
    allPeople()
    }

    override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
    }
})

 */

        refPersons.child(personID).removeValue()


}
fun allPeople(){

/*val job:Job = CoroutineScope(Dispatchers.Main).launch {
    personList.value = db.personDao().allPersonels()
}
 */
/* RETROFIT
personDao.allPersonels().enqueue(object : Callback<PersonResponse>{
override fun onResponse(
    call: Call<PersonResponse>,
    response: Response<PersonResponse>
) {
    val list = response.body()?.persons
    list?.let {
        personList.value = list!!
    }
}

override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
}
})
 */
    refPersons.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = ArrayList<Persons>()
            for (d in snapshot.children){
                val person = d.getValue(Persons::class.java)
                person?.let {
                    person.person_id = d.key
                    list.add(person)
                }
            }
            personList.value = list
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
}
fun savePerson(personName:String,personPhone: String){
/*val job:Job = CoroutineScope(Dispatchers.Main).launch {
val savePerson = Person(0,personName,personPhone)
db.personDao().insertPerson(savePerson)
}
*/
    val info = HashMap<String,Any>()
    info["person_name"] = personName
    info["person_phone"] = personPhone
    refPersons.push().setValue(info)
}
fun updatePerson(personID:String,personName:String,personPhone: String){

/* val job:Job = CoroutineScope(Dispatchers.Main).launch {
val updatePerson = Person(personID,personName,personPhone)
db.personDao().updatePerson(updatePerson)
}
*/
    val info = HashMap<String,Any>()
    info["person_name"] = personName
    info["person_phone"] = personPhone
    refPersons.child(personID).updateChildren(info)
}
}