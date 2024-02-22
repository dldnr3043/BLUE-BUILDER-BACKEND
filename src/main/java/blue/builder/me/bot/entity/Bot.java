package blue.builder.me.bot.entity;

import blue.builder.me.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "bot", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bot extends BaseTime  {
    @Id
    @Column(name = "bot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long botId;
    @Column(name = "bot_name")
    String botName;
    @Column(name = "channel_id")
    String channelId;

    @Builder
    public Bot(String botName, String channelId) {
        this.botName = botName;
        this.channelId = channelId;
    }
}
