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
import sportClub.telegramBot.user.cashe.DataCash;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class TelegramFacade {

    @Autowired
    DataCash dataCash;

    @Autowired
    BotStateContext context;

    public BotApiMethod<?> update(Update update){
        Message message = update.getMessage();

        if(message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            return handleCommandMessage(message);
        } else if(dataCash.getCurrentBotState(message.getFrom().getId()) != null){
            return handleBotStatus(message);
        }

        return null;
    }

    private SendMessage handleCommandMessage(Message message){
        String inputMessage;
        long userId = message.getFrom().getId();

        if((inputMessage = message.getText()) != null)
            switch(inputMessage) {
                case "/start":
                    dataCash.saveCurrentBotState(userId, BotState.MENU_REGISTRATION);
                    return context.processInput(BotState.MENU_REGISTRATION, message);
            }

        return null;
    }

    private SendMessage handleBotStatus(Message message){
        long userId = message.getFrom().getId();

        switch(dataCash.getCurrentBotState(userId)) {
            case MENU_REGISTRATION -> context.processInput(BotState.INPUT_PHONE_NUMBER, message);
        }

        return null;
    }
}
