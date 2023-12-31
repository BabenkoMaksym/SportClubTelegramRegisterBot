package sportClub.telegramBot.user.cashe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public UserCash(@JsonProperty("user") User user, @JsonProperty("botState") BotState botState) {
        this.user = user;
        this.botState = botState;
    }
}
