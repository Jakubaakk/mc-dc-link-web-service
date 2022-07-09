package cz.jakubfilko.mcdclink.repository.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue
    var id: UUID?,
    var minecraftNickname: String?,
    var discordId: Int?,
    var verified: Boolean = false
) {
}