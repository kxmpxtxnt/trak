package fyi.pauli.trak.ui.font

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import fyi.pauli.app.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun ProximaNovaFontFamily() = FontFamily(
  Font(Res.font.proximanova_regular, FontWeight.Normal),
  Font(Res.font.proximanova_bold, FontWeight.Bold),
  Font(Res.font.proximanova_light, FontWeight.Light),
  Font(Res.font.proximanova_extrabold, FontWeight.ExtraBold),
  Font(Res.font.proximanova_black, FontWeight.Black),
)

@Composable
fun ProximaNovaTypography() = Typography().run {
  val fontFamily = ProximaNovaFontFamily()
  copy(
    displayLarge = displayLarge.copy(fontFamily = fontFamily),
    displayMedium = displayMedium.copy(fontFamily = fontFamily),
    displaySmall = displaySmall.copy(fontFamily = fontFamily),
    headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
    headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
    headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
    titleLarge = titleLarge.copy(fontFamily = fontFamily),
    titleMedium = titleMedium.copy(fontFamily = fontFamily),
    titleSmall = titleSmall.copy(fontFamily = fontFamily),
    bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
    bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
    bodySmall = bodySmall.copy(fontFamily = fontFamily),
    labelLarge = labelLarge.copy(fontFamily = fontFamily),
    labelMedium = labelMedium.copy(fontFamily = fontFamily),
    labelSmall = labelSmall.copy(fontFamily = fontFamily)
  )
}