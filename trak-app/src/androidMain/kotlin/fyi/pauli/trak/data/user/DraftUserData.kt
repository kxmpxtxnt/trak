package fyi.pauli.trak.data.user

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class DraftUserData(
    val draftPersonal: DraftPersonal?,
    val draftMeasurements: DraftMeasurements?,
) {

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