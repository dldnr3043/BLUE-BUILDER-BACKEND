package blue.builder.me.bot.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBot is a Querydsl query type for Bot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBot extends EntityPathBase<Bot> {

    private static final long serialVersionUID = 556360338L;

    public static final QBot bot = new QBot("bot");

    public final blue.builder.me.common.entity.QBaseTime _super = new blue.builder.me.common.entity.QBaseTime(this);

    public final StringPath botId = createString("botId");

    public final StringPath botName = createString("botName");

    public final StringPath channelId = createString("channelId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDttm = _super.regDttm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDttm = _super.updDttm;

    public QBot(String variable) {
        super(Bot.class, forVariable(variable));
    }

    public QBot(Path<? extends Bot> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBot(PathMetadata metadata) {
        super(Bot.class, metadata);
    }

}

