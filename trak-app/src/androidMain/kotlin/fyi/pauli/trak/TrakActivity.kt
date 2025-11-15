package fyi.pauli.trak

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import fyi.pauli.trak.screen.setup.Intro

class TrakActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            Navigator(Intro())
        }
    }
}