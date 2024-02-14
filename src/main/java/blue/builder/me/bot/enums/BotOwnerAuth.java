package blue.builder.me.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BotOwnerAuth {
    MASTER("마스터"),
    NORMAL("일반사용자");

    private String value;
}
