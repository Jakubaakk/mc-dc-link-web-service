package cz.jakubfilko.mcdclink.repository

import cz.jakubfilko.mcdclink.repository.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID> {
    fun findByMinecraftNickname(minecraftNickname: String): UserEntity?
    fun findByDiscordId(discordId: String): UserEntity?
}
