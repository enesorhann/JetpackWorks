package com.enesorhan.gptChat.views

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.enesorhan.gptChat.R
import com.enesorhan.gptChat.viewModel.GptViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ChatPage(viewModel: GptViewModel = hiltViewModel()) {

    val generatedMessage = viewModel.generatedMessage.value
    var text by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = Color.DarkGray,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Chat", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.orange),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(paddingValues)
                ) {

                    generatedMessage.takeIf { it.isNotEmpty() }?.let {
                        items(it) { message ->
                            val isUser = message.contains("Enes")
                            Card(
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = Color.DarkGray
                                ),
                                modifier = Modifier
                                    .padding(8.dp).fillMaxWidth().widthIn(0.dp,300.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                                ) {
                                    if (message.contains("http")) {

                                        GlideImage(
                                            model = message,
                                            contentDescription = "",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(200.dp)
                                                .background(color = Color.LightGray),
                                            loading = placeholder(R.drawable.baseline_downloading_24),
                                            failure = placeholder(R.drawable.baseline_error_24)

                                        )

                                    } else {
                                        Text(
                                            text = message,
                                            style = TextStyle(
                                                color = Color.LightGray,
                                                fontSize = 22.sp
                                            )
                                        )
                                    }

                                }

                            }


                        }

                    }
                }


                Card(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.DarkGray,
                        disabledContainerColor = Color.LightGray,
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TextField(
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.DarkGray,
                                focusedIndicatorColor = Color.White,
                                cursorColor = Color.White,
                                focusedTextColor = Color.White,
                                unfocusedLabelColor = Color.LightGray,
                                disabledTextColor = Color.White,
                                focusedLabelColor = Color.White,
                                disabledLabelColor = Color.LightGray,
                                unfocusedTextColor = Color.White,
                                disabledContainerColor = Color.DarkGray,
                                unfocusedContainerColor = Color.DarkGray,
                                disabledIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                            ),
                            modifier = Modifier.weight(0.60f),
                            value = text,
                            onValueChange = { text = it },
                            label = { Text(text = "Herhangi bir sey sor") },
                            minLines = 1,
                            maxLines = 10
                        )
                        Column {

                            OutlinedIconButton(
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.DarkGray
                                ),
                                onClick = {
                                    viewModel.createMessage(text)
                                    text = ""
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_send_24),
                                    contentDescription = ""
                                )
                            }
                            OutlinedIconButton(
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.DarkGray
                                ),
                                onClick = {
                                    viewModel.createImage(text)
                                    text = ""
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_image_24),
                                    contentDescription = ""
                                )
                            }
                        }
                    }

                }


            }
        }


    }
}