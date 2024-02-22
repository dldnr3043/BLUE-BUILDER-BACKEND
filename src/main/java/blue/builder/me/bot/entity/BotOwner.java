package blue.builder.me.bot.entity;

import blue.builder.me.bot.enums.BotOwnerAuth;
import blue.builder.me.common.entity.BaseTime;
import blue.builder.me.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "bot_owner", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BotOwner extends BaseTime  {
    @EmbeddedId
    BotOwnerId botOwnerId;
    @MapsId("email")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="email", referencedColumnName = "email")
    User user;
    @MapsId("botId")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="bot_id", referencedColumnName = "bot_id")
    Bot bot;
    @Column(name = "auth")
    @Enumerated(EnumType.STRING)
    BotOwnerAuth auth;

    @Builder
    public BotOwner(User user, Bot bot, BotOwnerAuth auth) {
        this.botOwnerId = new BotOwnerId(bot.getBotId(), user.getEmail());
        this.user = user;
        this.bot = bot;
        this.auth = auth;
    }
}
