package blue.builder.me.bot.controller;

import blue.builder.me.auth.dto.SignupDTO;
import blue.builder.me.bot.dto.BotListDTO;
import blue.builder.me.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BotController {
    private final BotService botService;
    @PostMapping("/api/bot-list/select")
    public JSONObject selectBotList(@RequestBody JSONObject reqObject) {
        JSONObject retObject = new JSONObject();

        List<BotListDTO> botList = botService.selectBotList(reqObject.get("email").toString());

        retObject.put("ERROR_FLAG", false);
        retObject.put("DATA", botList);

        return retObject;
    }
}
