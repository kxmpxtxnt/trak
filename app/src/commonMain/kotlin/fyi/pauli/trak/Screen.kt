package fyi.pauli.trak

import androidx.compose.runtime.Composable

interface Screen {

    val screen: @Composable () -> Unit
}