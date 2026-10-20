package com.example.campaingsandfriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.campaingsandfriends.ui.navegacion.BottomBar
import com.example.campaingsandfriends.ui.navegacion.TopBar
import com.example.campaingsandfriends.ui.theme.CampaingsAndFriendsTheme
import androidx.navigation.compose.rememberNavController
import com.example.campaingsandfriends.ui.navegacion.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CampaingsAndFriendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = {TopBar(navController = navController)},
                        bottomBar = {BottomBar(navController = navController)}
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )

                }
            }
        }
    }
}

