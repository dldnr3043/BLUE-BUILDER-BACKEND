package blue.builder.me.bot.service;

import blue.builder.me.bot.dto.BotListDTO;

import java.util.List;

public interface BotService {
    List<BotListDTO> selectBotList(String email);
}
