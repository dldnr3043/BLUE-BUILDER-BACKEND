package blue.builder.me.bot.repository;

import blue.builder.me.bot.dto.BotListDTO;
import com.querydsl.core.Tuple;

import java.util.List;

public interface BotListRepository {
    List<BotListDTO> findBotListByUserId(String email);
}
