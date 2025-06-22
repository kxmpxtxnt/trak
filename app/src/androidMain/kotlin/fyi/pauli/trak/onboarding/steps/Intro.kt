package fyi.pauli.trak.onboarding.steps

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fyi.pauli.trak.R
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.OnboardingScreen
import fyi.pauli.trak.onboarding.StepWithSuccessor

@Composable
fun Intro(navController: NavController) = OnboardingScreen(Onboarding.INTRO, navController) {
    var agreed by remember { mutableStateOf(false) }
    StepWithSuccessor(
        condition = agreed,
        navController = navController,
        successor = Onboarding.MEASUREMENTS
    ) {
        Row {
            Checkbox(checked = agreed, onCheckedChange = { agreed = it })

            Text(
                text = stringResource(R.string.onboarding_intro_agree),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
