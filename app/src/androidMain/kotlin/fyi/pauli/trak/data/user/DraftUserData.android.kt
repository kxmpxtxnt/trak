package fyi.pauli.trak.data.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import fyi.pauli.trak.TrakAndroid
import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.serialization.protoBuf
import kotlinx.coroutines.flow.first
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray

actual val draftUserData: LocalDataStore<DraftUserData> = DraftUserDataStore(TrakAndroid.instance.applicationContext)

private class DraftUserDataStore(private val context: Context) : LocalDataStore<DraftUserData>() {

    override suspend fun store(storable: DraftUserData) {
        context.userDataPreferences.edit { preferences ->
            preferences[userDataKey] = protoBuf.encodeToByteArray(preferences)
        }
    }

    override suspend fun load(): DraftUserData? {
        val byteArray = context.userDataPreferences.data.first()[userDataKey] ?: return null
        return protoBuf.decodeFromByteArray(byteArray)
    }
}