package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.serialization.json
import kotlinx.browser.window

actual val draftUserData: LocalDataStore<DraftUserData> = DraftUserDataStore()

private class DraftUserDataStore : LocalDataStore<DraftUserData>() {

    override suspend fun store(storable: DraftUserData) {
        window.localStorage.setItem(COMMON_USER_DATA_KEY, json.encodeToString(storable))
    }

    override suspend fun load(): DraftUserData? {
        val data = window.localStorage.getItem(COMMON_USER_DATA_KEY) ?: return null
        return json.decodeFromString(data)
    }
}