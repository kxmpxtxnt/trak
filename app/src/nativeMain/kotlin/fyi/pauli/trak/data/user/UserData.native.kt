package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.data.UserData
import platform.Foundation.NSUserDefaults

actual val userDataStore: LocalDataStore<UserData> = UserDataStore()

private class UserDataStore : LocalDataStore<UserData>() {

    val preferences = NSUserDefaults.standardUserDefaults

    override suspend fun store(storable: UserData) {
        preferences.setObject(storable, COMMON_USER_DATA_KEY)
    }

    override suspend fun load(): UserData? {
        return preferences.objectForKey(COMMON_USER_DATA_KEY) as? UserData
    }
}