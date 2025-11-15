package fyi.pauli.trak.screen.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import fyi.pauli.trak.ui.theme.Theme
import fyi.pauli.trak.ui.theme.TrakTheme

class Intro : Screen {

    @Composable
    override fun Content() = TrakTheme(Theme.YOU) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(40.dp))
            Box {
                Text(
                    text = "Intro",
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp
                )
            }
        }
    }

}