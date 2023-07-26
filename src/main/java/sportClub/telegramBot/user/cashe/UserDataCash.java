package sportClub.telegramBot.user.cashe;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import sportClub.telegramBot.botapi.BotState;
import sportClub.telegramBot.user.module.User;
import sportClub.telegramBot.user.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;


@Component
@Log4j2
public class UserDataCash implements DataCash {

    @Autowired
    private UserService service;

    @Autowired
    private RedisTemplate<String, UserCash> redisTemplate;

    @Override
    @Cacheable(key = "'userCash:' + #userId")
    public BotState getCurrentBotState(long userId) {
        UserCash userCash = redisTemplate.opsForValue().get("userCash:" + userId);

        if (userCash == null) {
            userCash = saveUserToCash(userId);
            userCash.setBotState(BotState.MENU_REGISTRATION);
            return BotState.MENU_REGISTRATION;
        }
        log.info("Fetching current bot state for user with ID: {}", userId);
        return userCash.getBotState();
    }

    @Override
    @CachePut(key = "'userCash:' + #userId")
    public void saveCurrentBotState(long userId, BotState botState) {
        if (botState != null) {
            UserCash userCash = redisTemplate.opsForValue().get("userCash:" + userId);
            if (userCash == null)
                userCash = saveUserToCash(userId);
            userCash.setBotState(botState);
            redisTemplate.opsForValue().set("userCash:" + userId, userCash);
            log.info("Saving bot state {} for user with ID: {}", botState, userId);
        }
    }

    private UserCash saveUserToCash(long userId) {
        User user = service.getUserById(userId);

        UserCash userCash = UserCash.builder()
                .user(user)
                .botState(null).build();

        redisTemplate.opsForValue().set("userCash:" + userId, userCash);
        return userCash;
    }
}
