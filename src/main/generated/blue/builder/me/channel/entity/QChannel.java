package blue.builder.me.channel.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChannel is a Querydsl query type for Channel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChannel extends EntityPathBase<Channel> {

    private static final long serialVersionUID = -1796418030L;

    public static final QChannel channel = new QChannel("channel");

    public final blue.builder.me.common.entity.QBaseTime _super = new blue.builder.me.common.entity.QBaseTime(this);

    public final StringPath channelId = createString("channelId");

    public final StringPath channelName = createString("channelName");

    public final StringPath messenger = createString("messenger");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDttm = _super.regDttm;

    public final StringPath senderKey = createString("senderKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDttm = _super.updDttm;

    public QChannel(String variable) {
        super(Channel.class, forVariable(variable));
    }

    public QChannel(Path<? extends Channel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChannel(PathMetadata metadata) {
        super(Channel.class, metadata);
    }

}

