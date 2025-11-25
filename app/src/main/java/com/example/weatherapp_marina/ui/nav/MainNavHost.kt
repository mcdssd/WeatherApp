package com.example.weatherapp_marina.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp_marina.model.MainViewModel
import com.example.weatherapp_marina.ui.HomePage
import com.example.weatherapp_marina.ui.ListPage
import com.example.weatherapp_marina.ui.MapPage

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = Route.Home) {
        composable<Route.Home> { HomePage(viewModel = viewModel)}
        composable<Route.List> {  ListPage(viewModel = viewModel) }
        composable<Route.Map> { MapPage(viewModel = viewModel) }
    }
}