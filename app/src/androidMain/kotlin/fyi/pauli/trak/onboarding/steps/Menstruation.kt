package fyi.pauli.trak.onboarding.steps

import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.OnboardingScreen


@Composable
fun Menstruation() {
  OnboardingScreen(Onboarding.MENSTRUATION) {
    var checked by remember { mutableStateOf(true) }

    Switch(
      checked = checked,
      onCheckedChange = {
        checked = it
      },
    )
  }
}
