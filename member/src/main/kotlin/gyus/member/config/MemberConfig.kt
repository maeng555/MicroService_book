package gyus.member.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.authentication.PasswordEncoderParser
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

//씨큐리티 설정 권한은 거의 다허용 마이크로서비스중점으로 공부
@Configuration
class MemberConfig {
    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder() // 암호화 인코더 함수
    }
    @Bean
    fun securityFilterChain(http:HttpSecurity):SecurityFilterChain{
        http
            .csrf { it.disable() }
            .httpBasic{}
            .authorizeHttpRequests { authorize -> authorize.anyRequest().permitAll() }
        return http.build()
    }

}