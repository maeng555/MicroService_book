package gyus.member.model

import com.fasterxml.jackson.annotation.JsonIgnore



class MemberDTO(

    val id: Long? = 0,

    val name: String,

    val email: String,
    @JsonIgnore
    val password: String = "",

    val type: String = "NORMAL"
) {
    companion object {
        fun toDto(entity: BaseMember): MemberDTO {
            val type = when (entity) {
                is Member -> "NORMAL"
                is Author -> "AUTHOR"
                else -> "UNKNOWN"
            }
            return MemberDTO(
                id = entity.id,
                name = entity.name,
                email = entity.email,
                type = type,
                password = entity.password
            )
        }
    }
}