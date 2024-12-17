package fyi.pauli.trak.onboarding.steps

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fyi.pauli.trak.R
import fyi.pauli.trak.onboarding.Onboarding
import fyi.pauli.trak.onboarding.OnboardingScreen

@Preview
@Composable
fun Intro() = OnboardingScreen(Onboarding.INTRO) {
  var agreed by remember { mutableStateOf(false) }
  Scaffold(
    floatingActionButton = {
      Button(
        onClick = { },
        modifier = Modifier.defaultMinSize(minWidth = 56.dp, minHeight = 56.dp),
        enabled = agreed,
        shape = CircleShape
      ) {
        Text(stringResource(R.string.onboarding_intro_next), fontSize = 20.sp)
      }
    }
  ) {
    Spacer(Modifier.size(10.dp))
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
