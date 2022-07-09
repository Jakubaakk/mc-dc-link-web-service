package cz.jakubfilko.mcdclink.service

import cz.jakubfilko.mcdclink.dto.UserDto
import cz.jakubfilko.mcdclink.exception.UserIdAlreadySetException
import cz.jakubfilko.mcdclink.exception.UserIdCannotBeChangedException
import cz.jakubfilko.mcdclink.exception.UserNotFoundException
import cz.jakubfilko.mcdclink.mapper.fromUserDtoToUserEntity
import cz.jakubfilko.mcdclink.mapper.fromUserEntityToUserDto
import cz.jakubfilko.mcdclink.repository.UserRepository
import cz.jakubfilko.mcdclink.repository.entity.UserEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers() = userRepository.findAll().map { fromUserEntityToUserDto(it) }

    fun getUserByMinecraftNickname(minecraftNickname: String): UserDto {
        return fromUserEntityToUserDto(getUserEntityByMinecraftName(minecraftNickname))
    }

    fun getUserByDiscordId(discordId: String): UserDto {
        return fromUserEntityToUserDto(getUserEntityByDiscordId(discordId))
    }

    fun createUser(user: UserDto): UserDto {
        if (user.id != null) {
            throw UserIdAlreadySetException("Cannot create user: $user with already set id")
        }
        return fromUserEntityToUserDto(userRepository.save(fromUserDtoToUserEntity(user)))
    }

    fun updateUser(userId: UUID, user: UserDto): UserDto {
        if (userId != user.id) {
            throw UserIdCannotBeChangedException("Cannot change userId from: $userId to ${user.id}")
        }
        userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException("User with id: $userId was not found")
        return fromUserEntityToUserDto(userRepository.save(fromUserDtoToUserEntity(user)))
    }

    fun updateVerifyStatusForUserId(userId: UUID, status: Boolean): UserDto {
        val entity = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException("User with id: $userId was not found")
        entity.verified = status
        return fromUserEntityToUserDto(userRepository.save(entity))
    }

    fun updateUserByDiscordId(discordId: String, user: UserDto): UserDto {
        val entity = getUserEntityByDiscordId(discordId)
        return fromUserEntityToUserDto(updateUserEntity(entity, fromUserDtoToUserEntity(user)))
    }

    fun updateUserByMinecraftNickname(minecraftNickname: String, user: UserDto): UserDto {
        val entity = getUserEntityByMinecraftName(minecraftNickname)
        return fromUserEntityToUserDto(updateUserEntity(entity, fromUserDtoToUserEntity(user)))
    }

    private fun getUserEntityByDiscordId(discordId: String) = userRepository.findByDiscordId(discordId)
        ?: throw UserNotFoundException("User with discordId: $discordId was not found")

    private fun getUserEntityByMinecraftName(minecraftNickname: String) = userRepository.findByMinecraftNickname(minecraftNickname)
        ?: throw UserNotFoundException("User with minecraft nickname: $minecraftNickname was not found")

    fun updateUserEntity(oldUser: UserEntity, newUser: UserEntity): UserEntity {
        if (oldUser.id != newUser.id) {
            throw UserIdCannotBeChangedException("Cannot change userId from: ${oldUser.id} to ${newUser.id}")
        }
        return userRepository.save(newUser)
    }
}