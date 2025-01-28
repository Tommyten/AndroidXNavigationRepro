package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = StartDestination
                ) {
                    composable<StartDestination> {
                        Column {
                            Text("This is Start Destination")
                            Button({
                                navController.navigate(Uri.parse("test://testapp/deeplink")) {
                                    launchSingleTop = true
                                }
                                navController.navigate(Uri.parse("test://testapp/deeplink")) {
                                    launchSingleTop = true
                                }
                            }) {
                                Text("navigate")
                            }
                        }
                    }
                    composable<DeeplinkDestination>(
                        deepLinks = listOf(navDeepLink<DeeplinkDestination>("test://testapp/deeplink"))
                    ) {
                        Text("Hello, this is Deeplink Destination calling")
                    }
                }
            }
        }
    }
}

@Serializable
object StartDestination

@Serializable
object DeeplinkDestination
