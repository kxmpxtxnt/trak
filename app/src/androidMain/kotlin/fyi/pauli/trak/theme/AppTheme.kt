package fyi.pauli.trak.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import fyi.pauli.trak.ui.font.ProximaNovaTypography
import fyi.pauli.trak.ui.theme.Theme
import fyi.pauli.trak.ui.theme.period.PeriodTheme
import fyi.pauli.trak.ui.theme.together.TogetherTheme
import fyi.pauli.trak.ui.theme.you.YouTheme

@Composable
fun AppTheme(
  theme: Theme = Theme.YOU,
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    isSystemInDarkTheme() -> when (theme) {
      Theme.YOU -> YouTheme.darkScheme
      Theme.MENSTRUATION -> PeriodTheme.darkScheme
      Theme.TOGETHER -> TogetherTheme.darkScheme
    }

    else -> when (theme) {
      Theme.YOU -> YouTheme.lightScheme
      Theme.MENSTRUATION -> PeriodTheme.lightScheme
      Theme.TOGETHER -> TogetherTheme.lightScheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = ProximaNovaTypography(),
    content = content
  )
}
