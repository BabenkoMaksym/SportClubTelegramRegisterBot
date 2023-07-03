package sportClub.telegramBot.user.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sportClub.telegramBot.user.module.User;
import sportClub.telegramBot.user.repository.jdbc.UserJdbcRepository;
import sportClub.telegramBot.user.repository.jpa.UserJpaRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserJdbcRepository userJdbcRepository;

    public User getUserById(long userId) {
        return userJdbcRepository.findUserById(userId).orElse(null);
    }

    public void saveUser(User user) {
        userJpaRepository.save(user);
    }
}
