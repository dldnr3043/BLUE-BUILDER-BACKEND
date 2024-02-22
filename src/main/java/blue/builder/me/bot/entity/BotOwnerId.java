package blue.builder.me.bot.entity;

import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Embeddable
public class BotOwnerId implements Serializable {
    private Long botId;
    private String email;

    public BotOwnerId(Long botId, String email) {
        this.botId = botId;
        this.email = email;
    }
}
