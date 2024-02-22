package blue.builder.me.bot.service;

import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.entity.Bot;
import blue.builder.me.bot.entity.BotOwner;
import blue.builder.me.bot.entity.QBot;
import blue.builder.me.bot.entity.QBotOwner;
import blue.builder.me.bot.enums.BotOwnerAuth;
import blue.builder.me.bot.repository.BotOwnerRepository;
import blue.builder.me.bot.repository.BotRepository;
import blue.builder.me.channel.entity.QChannel;
import blue.builder.me.user.entity.User;
import blue.builder.me.user.repository.UserRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {
    private final BotRepository botRepository;
    private final BotOwnerRepository botOwnerRepository;
    private final UserRepository userRepository;

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

    @Override
    public JSONObject insertBot(String botName, String email, BotOwnerAuth auth) {
        User user = userRepository.findById(email).orElseThrow(()->new RuntimeException("해당 email을 가진 user가 없습니다."));
        JSONObject retObject = new JSONObject();

        // 1. save to bot table
        Bot bot = Bot.builder()
                .botName(botName)
                .build();
        Bot savedBot = botRepository.save(bot);

        // 2. save to bot owner table
        BotOwner botOwner = BotOwner.builder()
                .bot(bot)
                .user(user)
                .auth(auth)
                .build();
        BotOwner savedBotOwner = botOwnerRepository.save(botOwner);

        if(savedBot.getBotId() != null && savedBotOwner.getBot() != null) {
            retObject.put("ERROR_FLAG", false);
        }
        else {
            retObject.put("ERROR_FLAG", true);
            retObject.put("ERROR_MSG", "봇 삽입 작업이 실패했습니다.");
        }
        return retObject;
    }
}
