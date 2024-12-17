package fyi.pauli.trak.ui.font

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import fyi.pauli.app.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily() = FontFamily(
  Font(Res.font.Montserrat_Black, FontWeight.Black),
  Font(Res.font.Montserrat_BlackItalic, FontWeight.Black, FontStyle.Italic),

  Font(Res.font.Montserrat_ExtraLight, FontWeight.ExtraLight),
  Font(Res.font.Montserrat_ExtraLightItalic, FontWeight.ExtraLight, FontStyle.Italic),

  Font(Res.font.Montserrat_Light, FontWeight.Light),
  Font(Res.font.Montserrat_LightItalic, FontWeight.Light, FontStyle.Italic),

  Font(Res.font.Montserrat_Thin, FontWeight.Thin),
  Font(Res.font.Montserrat_ThinItalic, FontWeight.Thin, FontStyle.Italic),

  Font(Res.font.Montserrat_Medium, FontWeight.Medium),
  Font(Res.font.Montserrat_MediumItalic, FontWeight.Medium, FontStyle.Italic),

  Font(Res.font.Montserrat_Regular, FontWeight.Normal),
  Font(Res.font.Montserrat_Italic, FontWeight.Normal, FontStyle.Italic),

  Font(Res.font.Montserrat_SemiBold, FontWeight.SemiBold),
  Font(Res.font.Montserrat_SemiBoldItalic, FontWeight.SemiBold, FontStyle.Italic),

  Font(Res.font.Montserrat_Bold, FontWeight.Bold),
  Font(Res.font.Montserrat_BoldItalic, FontWeight.Bold, FontStyle.Italic),

  Font(Res.font.Montserrat_ExtraBold, FontWeight.ExtraBold),
  Font(Res.font.Montserrat_ExtraBoldItalic, FontWeight.ExtraBold, FontStyle.Italic),
)

@Composable
fun MontserratTypography() = Typography().run {
  val fontFamily = MontserratFontFamily()
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