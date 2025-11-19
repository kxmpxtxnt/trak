package fyi.pauli.trak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import fyi.pauli.trak.data.user.draftUserData
import fyi.pauli.trak.screen.Setup

class TrakActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val draftData by draftUserData.collectAsState(null)

            println("IsFinished: ${draftData?.isFinished}")

            if (!(draftData?.isFinished ?: false)) {
                Navigator(Setup.Intro)
            }
        }
    }
}