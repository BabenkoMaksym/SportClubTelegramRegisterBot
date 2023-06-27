package sportClub.telegramBot.botapi;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class TelegramFacade {

    @Autowired
    BotStateContext context;

    public BotApiMethod<?> update(Update update){
        Message message = update.getMessage();

        if(message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            return handleCommandMessage(message);
        }

        return null;
    }

    private SendMessage handleCommandMessage(Message message){
        String inputMessage;
        long userId = message.getFrom().getId();

        return null;
    }
}
