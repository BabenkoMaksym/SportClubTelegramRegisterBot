package sportClub.telegramBot.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sportClub.telegramBot.user.module.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
