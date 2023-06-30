package sportClub.telegramBot.user.cashe;

import sportClub.telegramBot.botapi.BotState;

public interface DataCashe {

    BotState getCurrentBotState(long userId);

    void saveCurrentBotState(long userId, BotState botState);
}
