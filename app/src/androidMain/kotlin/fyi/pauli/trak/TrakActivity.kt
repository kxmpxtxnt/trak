package fyi.pauli.trak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fyi.pauli.trak.ui.themes.AppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TrakActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    startKoin {
      androidLogger()
      androidContext(this@TrakActivity)
      modules(module {
        single { AppTheme() }
      })
    }

    setContent {
      Trak()
    }
  }
}

@Preview
@Composable
fun Trak(theme: AppTheme = get().get()) = theme {

}