package sportClub.telegramBot.user.cashe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sportClub.telegramBot.botapi.BotState;
import sportClub.telegramBot.user.module.User;
import sportClub.telegramBot.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCashe implements DataCashe {

    @Autowired
    UserService service;

    private Map<Long, UserCashe> botStateMap =  new HashMap<>();

    @Override
    public BotState getCurrentBotState(long userId) {
        UserCashe userCashe = botStateMap.get(userId);

        if(userCashe == null) {
            saveUserToCash(userId).setBotState(BotState.MENU_REGISTRATION);
            return BotState.MENU_REGISTRATION;
        }

        return userCashe.getBotState();
    }

    @Override
    public void saveCurrentBotState(long userId, BotState botState) {
        if(botState != null) {
            UserCashe userCashe = botStateMap.get(userId);
            if (userCashe == null)
                userCashe = saveUserToCash(userId);
            userCashe.setBotState(botState);
        }
    }

    private UserCashe saveUserToCash(long userId) {
        User user = service.getUserById(userId);

        UserCashe userCashe = UserCashe.builder()
                    .user(user)
                    .botState(null).build();
        botStateMap.put(userId, userCashe);
        return userCashe;
    }
}
