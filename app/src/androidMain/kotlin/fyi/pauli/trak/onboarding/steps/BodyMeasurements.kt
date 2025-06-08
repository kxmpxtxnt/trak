package fyi.pauli.trak.onboarding.steps

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.OnboardingScreen
import fyi.pauli.trak.onboarding.StepWithSuccessor
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyMeasurements(navController: NavController) =
    OnboardingScreen(Onboarding.INTRO, navController) {
        StepWithSuccessor(
            navController = navController, successor = Onboarding.MENSTRUATION
        ) {
            var height by remember { mutableDoubleStateOf(180.0) }
            var weight by remember { mutableDoubleStateOf(70.0) }
            var date by remember { mutableStateOf<LocalDate>(LocalDate.of(2000, Month.JANUARY, 1)) }
        }
    }