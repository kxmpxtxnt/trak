package fyi.pauli.trak.data.user

import android.content.Context
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import fyi.pauli.trak.serialization.protoBuf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray

private val Context.draftUserDataStore by preferencesDataStore("draft_user_data")

private val draftUserdataKey = byteArrayPreferencesKey("data")

val Context.draftUserData: Flow<DraftUserData?>
    get() = draftUserDataStore.data.map { preferences ->
        val data = preferences[draftUserdataKey] ?: return@map null
        protoBuf.decodeFromByteArray(data)
    }

@Serializable
data class DraftUserData(
    val draftPersonal: DraftPersonal?,
    val draftMeasurements: DraftMeasurements?,
    var isFinished: Boolean = false
) {

    suspend fun saveData(context: Context) {
        context.draftUserDataStore.edit { preferences ->
            preferences[draftUserdataKey] = protoBuf.encodeToByteArray(this)
        }
    }

    @Serializable
    data class DraftPersonal(
        val name: String?,
        val identifier: String?,
        val birthday: LocalDate?,
        val ableToMenstruate: Boolean?,
    )

    @Serializable
    data class DraftMeasurements(
        val height: Double?,
        val weight: Double?,
    )
}