package com.example.weatherapp_marina.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp_marina.model.MainViewModel
import com.example.weatherapp_marina.ui.theme.ForecastItem
import com.example.weatherapp_marina.R

@Composable
fun HomePage(viewModel: MainViewModel) {
    Column {
        if (viewModel.city == null) {
            Column( modifier = Modifier.fillMaxSize()
                .background(Color.Blue).wrapContentSize(Alignment.Center)
            ) {
                Text( text = "Selecione uma cidade!",
                    fontWeight = FontWeight.Bold, color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center, fontSize = 28.sp )
            }
        } else {
            Row {
//                Icon( imageVector = Icons.Filled.AccountBox,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(150.dp) )
                AsyncImage( // Substitui o Icon
                    model = viewModel.weather(viewModel.city!!).imgUrl,
                    modifier = Modifier.size(140.dp),
                    error = painterResource(id = R.drawable.loading_icon),
                    contentDescription = "Imagem"
                )

                Column {
                    Spacer(modifier = Modifier.size(12.dp))
                    Text( text = viewModel.city ?: "Selecione uma cidade...",
                        fontSize = 28.sp )
                    viewModel.city?.let { name ->
                        val weather = viewModel.weather(name)
                        Spacer(modifier = Modifier.size(12.dp))
                        Text( text = weather?.desc ?: "...",
                            fontSize = 22.sp )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text( text = "Temp: " + weather?.temp + "℃",
                            fontSize = 22.sp )
                    }
                }
            }
            viewModel.forecast(viewModel.city!!)?.let { forecasts ->
                LazyColumn {
                    items(items = forecasts) { forecast ->
                        ForecastItem(forecast, onClick = { })
                    }
                }
            }
        }
    }
}