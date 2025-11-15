package fyi.pauli.trak.data.user

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class UserData(
    val uuid: Uuid,
    val personal: Personal,
    val measurements: Measurements,
) {

    @Serializable
    data class Personal(
        val identifier: String,
        val name: String,
        val birthday: LocalDate,
        val isAbleToMenstruate: Boolean,
    )

    @Serializable
    data class Measurements(
        val height: Double,
        val weight: Double,
    )
}