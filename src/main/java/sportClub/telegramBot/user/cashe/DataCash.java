package sportClub.telegramBot.user.cashe;

import sportClub.telegramBot.botapi.BotState;

public interface DataCash {

    BotState getCurrentBotState(long userId);

    void saveCurrentBotState(long userId, BotState botState);
}
