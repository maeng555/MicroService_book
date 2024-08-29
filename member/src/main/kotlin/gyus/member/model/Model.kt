package gyus.member.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.time.Instant


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "member")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
abstract class BaseMember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0,
    @Column(length = 20)
    open val name: String,
    @Column(unique = true)
    open var email: String,
    open var providerId: String,
    open var role:String = "ROLE_MEMBER",
    open var password: String = "1234",
    open val createdDt: Instant = Instant.now(),
    open var updatedDt: Instant = Instant.now()
) {
    override fun toString(): String {
        return "Member(id : $id, name : $name, email : $email,  $role, $createdDt, $updatedDt)"
    }
}

@Entity
@DiscriminatorValue("NORMAL")
class Member(
    name: String,
    email: String,
    password: String,
    providerId: String = ""
) : BaseMember(name = name, email = email, password =  password, role= "ROLE_MEMBER", providerId =  providerId)

@Entity
@DiscriminatorValue("AUTHOR")
class Author(
    name: String,
    email: String,
    password: String,
    providerId: String = ""
) : BaseMember(name = name, email = email, password =  password, role= "ROLE_AUTHOR", providerId =  providerId)
