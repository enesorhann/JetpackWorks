package com.example.cookwork.views

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.cookwork.model.Cooks



@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailsPage(cook:Cooks){

    val activity = (LocalContext.current as Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = cook.yemek_adi!!, fontSize = 30.sp, fontWeight = FontWeight.Bold) }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            GlideImage(
                model = "http://kasimadalan.pe.hu/yemekler/resimler/${cook.yemek_resim_adi}",
                contentDescription = cook.yemek_resim_adi,
                modifier = Modifier.size(250.dp)
            )
            Text(text = "${cook.yemek_fiyat} ₺", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                onClick = {
                    Log.e("Cook","${cook.yemek_adi} siparis verildi")
            },
                modifier = Modifier.size(250.dp,50.dp)
                ) {
                Text(text = "Set Order", fontSize = 25.sp)

            }
        }
    }
}