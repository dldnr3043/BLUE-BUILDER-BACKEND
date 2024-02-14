package blue.builder.me.channel.repository;

import blue.builder.me.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * save()
 * findOne()
 * findAll() : sort, pageable
 * count()
 * delete()
 */
public interface ChannelRepository extends JpaRepository<Channel, String>  {
}
