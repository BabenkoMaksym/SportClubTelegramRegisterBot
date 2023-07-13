package sportClub.telegramBot.user.cashe;

import lombok.*;
import lombok.experimental.FieldDefaults;
import sportClub.telegramBot.botapi.BotState;
import sportClub.telegramBot.user.module.User;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCash {
    User user;
    BotState botState;
}
