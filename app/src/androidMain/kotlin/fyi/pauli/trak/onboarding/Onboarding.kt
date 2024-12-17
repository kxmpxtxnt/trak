package fyi.pauli.trak.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fyi.pauli.trak.R.drawable
import fyi.pauli.trak.R.string
import fyi.pauli.trak.theme.AppTheme
import fyi.pauli.trak.ui.theme.Theme

enum class Onboarding(
  val titleResource: Int,
  val textResource: Int,
  val painterResource: Int,
  val theme: Theme,
  enableNextButton: Boolean,
) {
  INTRO(
    string.onboarding_intro_title,
    string.onboarding_intro_text,
    drawable.onboarding_intro,
    Theme.YOU,
    false
  ),
  MENSTRUATION(
    string.onboarding_menstruation_title,
    string.onboarding_menstruation_text,
    drawable.onboarding_menstruation,
    Theme.MENSTRUATION,
    true
  ),
  //BODY,
  //TRAINING,
}

@Composable
fun OnboardingScreen(
  onboarding: Onboarding,
  capture: @Composable () -> Unit = {},
) = AppTheme(onboarding.theme) {
  val title = stringResource(onboarding.titleResource)
  val text = stringResource(onboarding.textResource)

  Scaffold(
    floatingActionButton = {

    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(Modifier.size(20.dp))

      Text(
        text = title,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        color = MaterialTheme.colorScheme.primary
      )

      Image(
        painter = painterResource(onboarding.painterResource),
        "Onboarding image for the $title setup.",
        modifier = Modifier
          .size(350.dp)
      )

      Box(
        Modifier
          .padding(horizontal = 10.dp)
          .clip(RoundedCornerShape(25.dp))
          .background(MaterialTheme.colorScheme.secondaryContainer)
      ) {
        Text(
          text = text,
          modifier = Modifier.padding(25.dp),
          fontWeight = FontWeight.Normal,
          fontSize = 20.sp,
          color = MaterialTheme.colorScheme.primary
        )
      }
      Spacer(Modifier.size(20.dp))

      capture()
    }
  }
}