package blue.builder.me.user.domain;

import blue.builder.me.common.domain.BaseTime;
import blue.builder.me.user.util.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "user", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {
    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private final UserRole role = UserRole.ROLE_NON_AUTH;

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
