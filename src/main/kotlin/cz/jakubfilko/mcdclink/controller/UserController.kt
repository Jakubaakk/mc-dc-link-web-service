package cz.jakubfilko.mcdclink.controller

import cz.jakubfilko.mcdclink.dto.StatusDto
import cz.jakubfilko.mcdclink.dto.UserDto
import cz.jakubfilko.mcdclink.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getUsers(): List<UserDto> {
        return userService.getAllUsers()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: UserDto): UserDto {
        return userService.createUser(user)
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id", required = true) id: UUID, @RequestBody user: UserDto): UserDto {
        return userService.updateUser(id, user)
    }

    @PutMapping("/{id}/status")
    fun updateUserStatusById(@PathVariable("id", required = true) id: UUID, @RequestBody status: StatusDto): UserDto {
        return userService.updateVerifyStatusForUserId(id, status.verified)
    }

    @GetMapping("/byMinecraftNickname/{minecraftNickname}")
    fun getUserByMinecraftNickname(
        @PathVariable(
            "minecraftNickname", required = true
        ) minecraftNickname: String
    ): UserDto {
        return userService.getUserByMinecraftNickname(minecraftNickname)
    }

    @PutMapping("/byMinecraftNickname/{minecraftNickname}")
    fun updateUserByMinecraftNickname(
        @PathVariable("minecraftNickname", required = true) minecraftNickname: String, @RequestBody user: UserDto
    ): UserDto {
        return userService.updateUserByMinecraftNickname(minecraftNickname, user)
    }

    @GetMapping("/byDiscordId/{discordId}")
    fun getUserByDiscordId(@PathVariable("discordId", required = true) discordId: Int): UserDto {
        return userService.getUserByDiscordId(discordId)
    }

    @PutMapping("/byDiscordId/{discordId}")
    fun updateUserByDiscordId(
        @PathVariable("discordId", required = true) discordId: Int, @RequestBody user: UserDto
    ): UserDto {
        return userService.updateUserByDiscordId(discordId, user)
    }

}
