package gyus.member.controller

import gyus.member.data.CreateMemberRequest
import gyus.member.data.UpdateMemberRequest
import gyus.member.model.MemberDTO
import gyus.member.service.MemberService

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

interface OnCreateError



@RequestMapping("/members")
@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping


    fun createMember(@Validated(OnCreateError::class) @RequestBody createMemberRequest: CreateMemberRequest, bindingResult: BindingResult): MemberDTO {
        if (bindingResult.hasErrors()) {
            val errorMessage = bindingResult.allErrors.joinToString { it.defaultMessage ?: "검증 에러" }
            throw RuntimeException(errorMessage)
        }
        return memberService.createMember(createMemberRequest.toMemberDTO())
    }

    @GetMapping
    fun memberList(@RequestParam page:Int = 1, @RequestParam  size:Int = 10): Page<MemberDTO> {
        val pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"))
        return memberService.memberList(pageable)
    }

    @GetMapping("/{id}")
    fun getMember(@PathVariable id:Long): MemberDTO {
        return memberService.getMember(id)
    }

    @PutMapping("/{id}")
    fun updateMember(@PathVariable id:Long, @RequestParam email:String): MemberDTO {
        val updateMemberRequest = UpdateMemberRequest(id, email)
        return memberService.updateMember(updateMemberRequest)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id:Long): String {
        memberService.deleteMember(id)
        return "OK"
    }
    @GetMapping("/in")
    fun getMembers(@RequestParam ids:List<Long>):List<MemberDTO>{
        return memberService.getMembers(ids)
    }
}