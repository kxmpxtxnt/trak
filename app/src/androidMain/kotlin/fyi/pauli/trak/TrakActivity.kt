package fyi.pauli.trak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.steps.Intro
import fyi.pauli.trak.onboarding.steps.Menstruation
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TrakActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    startKoin {
      androidLogger()
      androidContext(this@TrakActivity)
    }

    setContent {
      Trak()
    }
  }
}

@Preview
@Composable
fun Trak() {
  val navController = rememberNavController()
  NavHost(navController, startDestination = Onboarding.INTRO.name) {
    composable(Onboarding.INTRO.name) {
      Intro(navController)
    }

    composable(Onboarding.MENSTRUATION.name) {
      Menstruation(navController)
    }
  }
}