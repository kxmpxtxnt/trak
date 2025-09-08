package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import platform.Foundation.NSUserDefaults

actual val draftUserData: LocalDataStore<DraftUserData> = DraftUserDataStore()

private class DraftUserDataStore : LocalDataStore<DraftUserData>() {

    val preferences = NSUserDefaults.standardUserDefaults

    override suspend fun store(storable: DraftUserData) {
        preferences.setObject(storable, COMMON_USER_DATA_KEY)
    }

    override suspend fun load(): DraftUserData? {
        return preferences.objectForKey(COMMON_USER_DATA_KEY) as? DraftUserData
    }
}