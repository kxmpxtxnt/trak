package fyi.pauli.trak.screen.setup

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import fyi.pauli.trak.screen.SetupScreen
import fyi.pauli.trak.ui.theme.Theme

class Intro : SetupScreen(Theme.YOU, "Intro", content = {
    Text("Hello")
    Button({}) {
        Text("Button")
    }
})