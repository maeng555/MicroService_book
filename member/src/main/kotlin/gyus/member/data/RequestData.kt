package gyus.member.data

import gyus.member.model.MemberDTO

data class CreateMemberRequest (

    val name:String,

    val email:String
) {
    fun toMemberDTO(): MemberDTO {
        return MemberDTO(name = name, email = email)
    }
}

data class UpdateMemberRequest(
    val id: Long,
    val email:String
)