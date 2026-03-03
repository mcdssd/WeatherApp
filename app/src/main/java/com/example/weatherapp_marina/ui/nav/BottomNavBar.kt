package com.example.weatherapp_marina.ui.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp_marina.model.MainViewModel

@Composable
fun BottomNavBar(
    viewModel: MainViewModel,
    items: List<BottomNavItem>
) {
    NavigationBar(
        contentColor = Color.Black
    ) {

//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->

//            val isSelected =
//                navBackStackEntry?.destination
//                    ?.hasRoute(item.route::class) == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title,
                        fontSize = 12.sp)
                },
                alwaysShowLabel = true,
                selected = viewModel.page == item.route,
                onClick = {
                    viewModel.page = item.route
                }

//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.startDestinationId) {
//                            saveState = true
//                        }
//
//                        launchSingleTop = true
//
//                        restoreState = true
//                    }
//                }
            )
        }
    }
}