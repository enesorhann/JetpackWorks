package com.example.notesworkreply.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesworkreply.navigation.AppNavigation
import com.google.firebase.auth.FirebaseAuth

private val auth = FirebaseAuth.getInstance()

@Composable
fun RegisterPage(navController: NavController) {



    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordRepeat by remember {
        mutableStateOf("")
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                label = { Text(text = "Email") },
                value = email,
                onValueChange = { email = it })
            Spacer(modifier = Modifier.size(11.dp))
            TextField(
                label = { Text(text = "Password") },
                value = password,
                onValueChange = { password = it })
            Spacer(modifier = Modifier.size(11.dp))
            TextField(
                label = { Text(text = "Password Repeat") },
                value = passwordRepeat,
                onValueChange = { passwordRepeat = it })
            Spacer(modifier = Modifier.size(11.dp))
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty() && password == passwordRepeat){
                        auth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener{task->
                                if (task.isSuccessful){
                                    navController.navigate(AppNavigation.Login.route)
                                }else{

                                }
                            }
                    }

                }
            ) {
                Text(text = "Log-On")
            }
        }
    }
}