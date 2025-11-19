package fyi.pauli.trak.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import fyi.pauli.trak.screen.setup.Intro
import fyi.pauli.trak.ui.theme.Theme
import fyi.pauli.trak.ui.theme.TrakTheme

object Setup {
    val Intro = Intro()
}

open class SetupScreen(
    private val theme: Theme = Theme.YOU,
    private val title: String,
    private val content: @Composable () -> Unit
) : Screen {

    @Composable
    override fun Content() = TrakTheme(theme) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 45.sp
            )

            Spacer(Modifier.size(20.dp))

            content()
        }

    }

}