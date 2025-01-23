package fyi.pauli.trak.onboarding.steps

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.OnboardingScreen
import fyi.pauli.trak.onboarding.StepWithSuccessor

@Composable
fun Menstruation(navController: NavController) = OnboardingScreen(Onboarding.MENSTRUATION, navController) {
  StepWithSuccessor(
    navController = navController,
    successor = Onboarding.MENSTRUATION
  ) {
    Row(Modifier.fillMaxSize()) {
      Button({}, Modifier.fillMaxSize(0.5F)) {
        Text("YES")
      }
      Button({}, Modifier.fillMaxHeight(0.5F).fillMaxWidth()) {
        Text("NO")
      }
    }
  }
}
