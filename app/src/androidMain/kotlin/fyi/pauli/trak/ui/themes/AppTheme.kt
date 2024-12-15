package fyi.pauli.trak.ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fyi.pauli.trak.ui.themes.period.PeriodTheme
import fyi.pauli.trak.ui.themes.together.TogetherTheme
import fyi.pauli.trak.ui.themes.you.YouTheme

class AppTheme {
  private var activeTheme: Theme by mutableStateOf(Theme.PERIOD)

  enum class Theme {
    YOU,
    PERIOD,
    TOGETHER,
  }

  @Composable
  operator fun invoke(content: @Composable () -> Unit = {}) = when (activeTheme) {
    Theme.YOU -> YouTheme(content = content)
    Theme.PERIOD -> PeriodTheme(content = content)
    Theme.TOGETHER -> TogetherTheme(content = content)
  }
}

