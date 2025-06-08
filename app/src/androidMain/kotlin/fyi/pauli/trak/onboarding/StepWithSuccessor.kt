package fyi.pauli.trak.onboarding

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fyi.pauli.trak.R

@Composable
fun StepWithSuccessor(
    condition: Boolean = true,
    navController: NavController,
    successor: Onboarding,
    currentPage: @Composable () -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            Button(
                onClick = { navController.navigate(successor.name) },
                modifier = Modifier.defaultMinSize(minWidth = 56.dp, minHeight = 56.dp),
                enabled = condition,
                shape = CircleShape
            ) {
                Text(stringResource(R.string.onboarding_intro_next), fontSize = 20.sp)
            }
        }
    ) {
        currentPage()
    }
}