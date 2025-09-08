package fyi.pauli.trak.data.user

import fyi.pauli.trak.data.LocalDataStore
import fyi.pauli.trak.serialization.protoBuf
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.notExists
import kotlin.io.path.readBytes
import kotlin.io.path.writeBytes

actual val draftUserData: LocalDataStore<DraftUserData> = DraftUserDataStore()

private class DraftUserDataStore() : LocalDataStore<DraftUserData>() {

    private val userDataFile: Path = dataDirectory.resolve("user_data.dat").apply {
        if (notExists()) createFile()
    }

    override suspend fun store(storable: DraftUserData) {
        userDataFile.writeBytes(protoBuf.encodeToByteArray(storable))
    }

    override suspend fun load(): DraftUserData? {
        return protoBuf.decodeFromByteArray(userDataFile.readBytes())
    }
}