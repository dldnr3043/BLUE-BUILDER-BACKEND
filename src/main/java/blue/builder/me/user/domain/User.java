package blue.builder.me.user.domain;

import blue.builder.me.common.domain.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
