package blue.builder.me.bot.repository;

import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.entity.QBot;
import blue.builder.me.bot.entity.QBotOwner;
import blue.builder.me.bot.enums.BotOwnerAuth;
import blue.builder.me.channel.entity.QChannel;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BotListRepositoryImpl implements BotListRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> findBotListByUserId(String email) {
        QBot bot = QBot.bot;
        QChannel channel = QChannel.channel;
        QBotOwner botOwner = QBotOwner.botOwner;

        List<Tuple> results = jpaQueryFactory
                .select(bot.botId
                        , bot.botName
                        , channel.messenger.coalesce("없음").as(channel.messenger)
                        , channel.channelId
                        , channel.channelName.coalesce("없음").as(channel.channelName)
                        , botOwner.auth)
                .from(bot)
                .leftJoin(channel)
                .on(channel.channelId.eq(bot.channelId))
                .leftJoin(botOwner)
                .on(botOwner.bot.botId.eq(bot.botId))
                .on(botOwner.user.email.eq(email))
                .fetch();

        return results;
    }
}
