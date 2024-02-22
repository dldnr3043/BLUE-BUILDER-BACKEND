package blue.builder.me.bot.repository;

import blue.builder.me.bot.entity.BotOwner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * save()
 * findOne()
 * findAll() : sort, pageable
 * count()
 * delete()
 */
public interface BotOwnerRepository extends JpaRepository<BotOwner, String> {
}
