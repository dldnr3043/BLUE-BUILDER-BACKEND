package blue.builder.me.bot.service;

import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.repository.BotRepository;
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
        List<BotListDTO> results = botRepository.findBotListByUserId(email);

        return results;
    }
}
