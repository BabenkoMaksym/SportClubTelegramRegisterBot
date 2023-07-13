package sportClub.telegramBot.botapi.handlers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import sportClub.telegramBot.botapi.BotState;
import sportClub.telegramBot.botapi.InputHandler;
import sportClub.telegramBot.user.cashe.DataCash;
import sportClub.telegramBot.botapi.messageService.RegistrationMessageService;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationHandler implements InputHandler {
    @Autowired
    DataCash userDataCash;

    @Autowired
    RegistrationMessageService registrationMessageService;


    @Override
    public SendMessage handle(Message message) {
        return registrationMessageService.getRegistrationMenuService(
                message.getChatId(),
                "registration_message");
    }

    @Override
    public BotState getHandleName() {
        return BotState.MENU_REGISTRATION;
    }
}
