package cz.jakubfilko.mcdclink.dto

import java.util.UUID

class UserDto(
    val id: UUID? = null,
    val minecraftNickname: String?,
    val discordId: String?,
    val verified: Boolean = false) {
}

class StatusDto(
    val verified: Boolean
)
