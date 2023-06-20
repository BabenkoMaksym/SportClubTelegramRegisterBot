package sportClub.telegramBot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@ConfigurationProperties(prefix = "telegram-bot")
public class TelegramBot extends TelegramWebhookBot{

    String botUsername;
    String botToken;
    String botPath;

    public TelegramBot(){
        log.info(String.format("Bot starts:\t{BotUsername: %s, BotWebHookPath: %s}", botUsername, botPath));
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        //todo: receive a message from user and handle it

        //the code below is an example of echo answering
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chat_id = update.getMessage().getChatId().toString();
            try {
                execute(new SendMessage(chat_id, "You sent: " + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
