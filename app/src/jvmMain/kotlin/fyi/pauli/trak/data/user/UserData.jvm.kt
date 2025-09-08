package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.data.UserData
import fyi.pauli.trak.serialization.protoBuf
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import java.nio.file.Path
import kotlin.io.path.*

actual val userDataStore: LocalDataStore<UserData> = UserDataStore()

val dataDirectory: Path = Path(System.getenv()["INSTALLATION_DIRECTORY"] ?: "./").resolve("data").apply {
    if (parent.notExists()) parent.createDirectories()
    if (notExists()) createDirectory()
}

private class UserDataStore() : LocalDataStore<UserData>() {

    private val userDataFile: Path = dataDirectory.resolve("user_data.dat").apply {
        if (notExists()) createFile()
    }

    override suspend fun store(storable: UserData) {
        userDataFile.writeBytes(protoBuf.encodeToByteArray(storable))
    }

    override suspend fun load(): UserData? {
        return protoBuf.decodeFromByteArray(userDataFile.readBytes())
    }
}