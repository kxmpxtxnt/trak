package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.data.UserData
import fyi.pauli.trak.serialization.json
import kotlinx.browser.window

actual val userDataStore: LocalDataStore<UserData> = UserDataStore()

private class UserDataStore : LocalDataStore<UserData>() {

    override suspend fun store(storable: UserData) {
        window.localStorage.setItem(COMMON_USER_DATA_KEY, json.encodeToString(storable))
    }

    override suspend fun load(): UserData? {
        val data = window.localStorage.getItem(COMMON_USER_DATA_KEY) ?: return null
        return json.decodeFromString(data)
    }
}