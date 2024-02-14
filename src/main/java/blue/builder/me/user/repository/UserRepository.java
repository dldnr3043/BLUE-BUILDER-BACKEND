package blue.builder.me.user.repository;

import blue.builder.me.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * save()
 * findOne()
 * findAll() : sort, pageable
 * count()
 * delete()
 */
public interface UserRepository extends JpaRepository<User, String> {
}
