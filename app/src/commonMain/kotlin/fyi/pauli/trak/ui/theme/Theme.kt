package fyi.pauli.trak.ui.theme

import androidx.compose.material3.ColorScheme
import fyi.pauli.trak.ui.theme.period.PeriodTheme
import fyi.pauli.trak.ui.theme.together.TogetherTheme
import fyi.pauli.trak.ui.theme.you.YouTheme

enum class Theme(val lightScheme: ColorScheme, val darkScheme: ColorScheme) {
  YOU(YouTheme.lightScheme, YouTheme.darkScheme),
  MENSTRUATION(PeriodTheme.lightScheme, PeriodTheme.darkScheme),
  TOGETHER(TogetherTheme.lightScheme, TogetherTheme.darkScheme)
}