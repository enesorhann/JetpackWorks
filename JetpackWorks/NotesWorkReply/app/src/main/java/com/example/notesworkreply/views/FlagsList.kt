package com.example.notesworkreply.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.notesworkreply.viewModels.FlagsViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FlagList(viewModel: FlagsViewModel = hiltViewModel()) {

    val flags by viewModel.flags

    Scaffold { paddingValues ->

        if (flags.isEmpty()) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                ) {
                CircularProgressIndicator()
            }
        }else{
            LazyColumn(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(flags.count()) {
                    val flag = flags[it]
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {

                            Text(text = flag.name, fontSize = 25.sp)
                            GlideImage(model = flag.flag, contentDescription = "")
                        }
                    }
                }
            }
        }

    }
}

