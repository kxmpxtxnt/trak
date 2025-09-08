package fyi.pauli.trak.data.user

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import fyi.pauli.trak.TrakAndroid
import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.data.UserData
import fyi.pauli.trak.serialization.protoBuf
import kotlinx.coroutines.flow.first
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray

val Context.userDataPreferences by preferencesDataStore(COMMON_USER_DATA_KEY)
val userDataKey: Preferences.Key<ByteArray> = byteArrayPreferencesKey(COMMON_USER_DATA_KEY)

actual val userDataStore: LocalDataStore<UserData> = UserDataStore(TrakAndroid.instance.applicationContext)

private class UserDataStore(private val context: Context) : LocalDataStore<UserData>() {

    override suspend fun store(storable: UserData) {
        context.userDataPreferences.edit { preferences ->
            preferences[userDataKey] = protoBuf.encodeToByteArray(preferences)
        }
    }

    override suspend fun load(): UserData? {
        val byteArray = context.userDataPreferences.data.first()[userDataKey] ?: return null
        return protoBuf.decodeFromByteArray(byteArray)
    }
}