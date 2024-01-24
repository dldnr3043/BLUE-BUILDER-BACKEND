package blue.builder.me.user.repository;

import blue.builder.me.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * save()
 * findOne()
 * findAll() : sort, pageable
 * count()
 * delete()
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
