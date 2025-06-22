package fyi.pauli.trak.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import fyi.pauli.trak.ui.font.MontserratTypography
import fyi.pauli.trak.ui.theme.Theme

@Composable
fun AppTheme(
    theme: Theme,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = when {
            isSystemInDarkTheme() -> theme.darkScheme
            else -> theme.lightScheme
        },
        typography = MontserratTypography(),
        content = content
    )
}
