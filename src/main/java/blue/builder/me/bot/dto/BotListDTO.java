package blue.builder.me.bot.dto;

import blue.builder.me.bot.enums.BotOwnerAuth;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BotListDTO {
    private Long botId;
    private String botName;
    private String messenger;
    private String channelId;
    private String channelName;
    private BotOwnerAuth auth;

    @Builder
    public BotListDTO(Long botId, String botName, String messenger, String channelId, String channelName, BotOwnerAuth auth) {
        this.botId = botId;
        this.botName = botName;
        this.messenger = messenger;
        this.channelId = channelId;
        this.channelName = channelName;
        this.auth = auth;
    }
}
