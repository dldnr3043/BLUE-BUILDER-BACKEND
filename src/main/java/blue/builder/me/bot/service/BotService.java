package blue.builder.me.bot.service;

import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.enums.BotOwnerAuth;
import org.json.simple.JSONObject;

import java.util.List;

public interface BotService {
    List<BotListDTO> selectBotList(String email);
    JSONObject insertBot(String botName, String email, BotOwnerAuth auth);
}
