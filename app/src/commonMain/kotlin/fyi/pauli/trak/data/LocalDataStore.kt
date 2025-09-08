package fyi.pauli.trak.data

abstract class LocalDataStore<L> {

    abstract suspend fun store(storable: L)

    abstract suspend fun load(): L?
}