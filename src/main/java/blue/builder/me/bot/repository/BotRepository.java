package blue.builder.me.bot.repository;

import blue.builder.me.bot.domain.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * save()
 * findOne()
 * findAll() : sort, pageable
 * count()
 * delete()
 */
public interface BotRepository extends JpaRepository<Bot, String> {
}
