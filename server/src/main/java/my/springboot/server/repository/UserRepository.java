package my.springboot.server.repository;

import my.springboot.server.entity.User;
import my.springboot.server.entity.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserPK> {
}
