package gyus.member

import gyus.member.model.Author
import gyus.member.model.Member
import gyus.member.repository.MemberRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class DataInit(
    private val memberRepository: MemberRepository,

    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        // post 추가

        for (i in 1..10) {
            val member = Member(name = "member$i", email = "member$i@email.com", password = passwordEncoder.encode("1234$i"))
            memberRepository.save(member)


        }


        val author = Author(name="author1", email="author1@email.com", password= passwordEncoder.encode("1234"))
        memberRepository.save(author)
    }
}