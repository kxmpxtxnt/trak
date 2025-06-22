package fyi.pauli.trak.onboarding.steps

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import fyi.pauli.trak.R
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.OnboardingScreen
import fyi.pauli.trak.onboarding.StepWithSuccessor

@Composable
fun Menstruation(navController: NavController) = OnboardingScreen(Onboarding.MENSTRUATION, navController) {
    var nullableAble: Boolean? by remember { mutableStateOf(null) }
    StepWithSuccessor(
        condition = nullableAble != null,
        navController = navController,
        successor = Onboarding.MENSTRUATION
    ) {
        Row(Modifier.fillMaxSize()) {
            SingleChoiceSegmentedButtonRow {
                SegmentedButton(
                    selected = (nullableAble ?: false),
                    shape = SegmentedButtonDefaults.baseShape,
                    onClick = { nullableAble = true },
                    label = { R.string.general_yes }
                )
                SegmentedButton(
                    selected = (nullableAble?.not() ?: false),
                    shape = SegmentedButtonDefaults.baseShape,
                    onClick = { nullableAble = false },
                    label = { R.string.general_no })
            }
        }
    }
}
