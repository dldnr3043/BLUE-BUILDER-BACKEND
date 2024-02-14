package blue.builder.me.bot.repository;

import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.entity.QBot;
import blue.builder.me.bot.entity.QBotOwner;
import blue.builder.me.channel.entity.QChannel;
import com.querydsl.core.Tuple;
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
    public List<BotListDTO> findBotListByUserId(String email) {
        QBot bot = QBot.bot;
        QChannel channel = QChannel.channel;
        QBotOwner botOwner = QBotOwner.botOwner;

        List<Tuple> results = jpaQueryFactory
                .select()
                .from(bot)
                .leftJoin(channel)
                .on(channel.channelId.eq(bot.channelId))
                .leftJoin(botOwner)
                .on(botOwner.bot.botId.eq(bot.botId))
                .on(botOwner.user.email.eq(email))
                .fetch();

        List<BotListDTO> resultList = new ArrayList<>();
        results.stream().forEach(r->{
            BotListDTO botListDTO = BotListDTO.builder()
                    .botId(r.get(bot.botId))
                    .botName(r.get(bot.botName))
                    .messenger(r.get(channel.messenger))
                    .channelId(r.get(channel.channelId))
                    .channelName(r.get(channel.channelName))
                    .auth(r.get(botOwner.auth))
                    .build();

            resultList.add(botListDTO);
        });

        return resultList;
    }
}
