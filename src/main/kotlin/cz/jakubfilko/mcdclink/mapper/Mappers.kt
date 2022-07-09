package cz.jakubfilko.mcdclink.mapper

import cz.jakubfilko.mcdclink.dto.UserDto
import cz.jakubfilko.mcdclink.repository.entity.UserEntity

fun fromUserEntityToUserDto(userEntity: UserEntity) = UserDto(
    id = userEntity.id,
    minecraftNickname = userEntity.minecraftNickname,
    discordId = userEntity.discordId,
    verified = userEntity.verified
)

fun fromUserDtoToUserEntity(userDto: UserDto) = UserEntity(
    id = userDto.id,
    minecraftNickname = userDto.minecraftNickname,
    discordId = userDto.discordId,
    verified = userDto.verified
)