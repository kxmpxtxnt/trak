package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.data.UserData

const val COMMON_USER_DATA_KEY: String = "trak_user_data"

expect val userDataStore: LocalDataStore<UserData>