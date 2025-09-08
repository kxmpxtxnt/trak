package fyi.pauli.trak

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TrakAndroid : ComponentActivity() {

    companion object {
        lateinit var instance: TrakAndroid
            private set
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this

        val androidModule = module {
            single { this@TrakAndroid.baseContext }
        }

        startKoin {
            androidLogger()
            androidContext(this@TrakAndroid)

            modules(androidModule)
        }

        setContent {
            Trak()
        }
    }
}