package blue.builder.me.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2112347998L;

    public static final QUser user = new QUser("user");

    public final blue.builder.me.common.entity.QBaseTime _super = new blue.builder.me.common.entity.QBaseTime(this);

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDttm = _super.regDttm;

    public final EnumPath<blue.builder.me.user.enums.UserRole> role = createEnum("role", blue.builder.me.user.enums.UserRole.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDttm = _super.updDttm;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

