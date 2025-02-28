package com.example.cookwork.views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.cookwork.R
import com.example.cookwork.factory.MainPageFactory
import com.example.cookwork.viewModel.MainPageViewModel
import com.google.gson.Gson


@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MainPage(navController: NavController){

    val context = LocalContext.current
    val viewModel:MainPageViewModel = viewModel(
        factory = MainPageFactory(context.applicationContext as Application)
    )
    val cookList = viewModel.liveList.observeAsState(listOf())
    val activity = (LocalContext.current as Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cooks")}
            )
        }
    ) {
        LazyColumn(Modifier.padding(it)) {
            items(
                count = cookList.value!!.count(),
                itemContent = {
                    val cook = cookList.value[it]
                    ElevatedCard(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 5.dp)
                            .clickable {
                                val objToJson = Gson().toJson(cook)
                                navController.navigate("detailspage/${objToJson}")
                            }
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(all = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                            ) {
                            GlideImage(
                                model = "http://kasimadalan.pe.hu/yemekler/resimler/${cook.yemek_resim_adi}",
                                contentDescription = cook.yemek_resim_adi,
                                modifier = Modifier.size(70.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Row(Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(
                                    Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.Start) {
                                    Text(text = cook.yemek_adi!!, fontSize = 20.sp)
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Text(
                                        text = "${cook.yemek_fiyat} ₺",
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        val objToJson = Gson().toJson(cook)
                                        navController.navigate("detailspage/${objToJson}")
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                                        contentDescription = "",
                                        tint = Color.Black,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}