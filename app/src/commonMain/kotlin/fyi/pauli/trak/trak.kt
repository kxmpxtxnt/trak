package fyi.pauli.trak

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import fyi.pauli.trak.data.user.DraftUserData
import fyi.pauli.trak.data.user.draftUserData
import fyi.pauli.trak.screens.Setup
import fyi.pauli.trak.screens.setup.Intro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private val job: Job = Job()
private val coroutineContext: CoroutineContext = Dispatchers.Default + job

@Composable
fun Trak() {
    val scope = rememberCoroutineScope(::coroutineContext)

    val (userData, setUserData) = remember { mutableStateOf<DraftUserData?>(null) }

    scope.launch { setUserData(draftUserData.load()) }

    if (userData == null) {
        Setup.Intro()
    }
}