package blue.builder.me.bot.service;

import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.entity.QBot;
import blue.builder.me.bot.entity.QBotOwner;
import blue.builder.me.bot.repository.BotRepository;
import blue.builder.me.channel.entity.QChannel;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {
    private final BotRepository botRepository;

    @Override
    public List<BotListDTO> selectBotList(String email) {
        QBot bot = QBot.bot;
        QChannel channel = QChannel.channel;
        QBotOwner botOwner = QBotOwner.botOwner;

        List<Tuple> results = botRepository.findBotListByUserId(email);

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
