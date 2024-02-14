package blue.builder.me.channel.entity;

import blue.builder.me.common.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "channel", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel extends BaseTime  {
    @Id
    @Column(name = "channel_id")
    String channelId;
    @Column(name = "channel_name")
    String channelName;
    @Column(name = "messenger")
    String messenger;
    @Column(name = "sender_key")
    String senderKey;

    @Builder
    public Channel(String channelId, String channelName, String messenger, String senderKey) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.messenger = messenger;
        this.senderKey = senderKey;
    }
}
