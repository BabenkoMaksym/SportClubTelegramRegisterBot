package sportClub.telegramBot.user.cashe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sportClub.telegramBot.botapi.BotState;
import sportClub.telegramBot.user.module.User;
import sportClub.telegramBot.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCash implements DataCash {

    @Autowired
    UserService service;

    private Map<Long, UserCash> botStateMap =  new HashMap<>();

    @Override
    public BotState getCurrentBotState(long userId) {
        UserCash userCash = botStateMap.get(userId);

        if(userCash == null) {
            saveUserToCash(userId).setBotState(BotState.MENU_REGISTRATION);
            return BotState.MENU_REGISTRATION;
        }

        return userCash.getBotState();
    }

    @Override
    public void saveCurrentBotState(long userId, BotState botState) {
        if(botState != null) {
            UserCash userCash = botStateMap.get(userId);
            if (userCash == null)
                userCash = saveUserToCash(userId);
            userCash.setBotState(botState);
        }
    }

    private UserCash saveUserToCash(long userId) {
        User user = service.getUserById(userId);

        UserCash userCash = UserCash.builder()
                    .user(user)
                    .botState(null).build();
        botStateMap.put(userId, userCash);
        return userCash;
    }
}
