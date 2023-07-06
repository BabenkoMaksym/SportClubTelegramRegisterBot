package sportClub.telegramBot;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import sportClub.telegramBot.botapi.TelegramFacade;

@Slf4j
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class TelegramBot extends TelegramWebhookBot{

    @Value("${telegram-bot.botUsername}")
    String botUsername;
    @Value("${telegram-bot.botToken}")
    String botToken;
    @Value("${telegram-bot.botPath}")
    String botPath;

    @Autowired
    TelegramFacade telegramFacade;

    @PostConstruct
    public void printState(){
        log.info(String.format("Bot starts:\t{BotUsername: %s, BotWebHookPath: %s}", botUsername, botPath));
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.update(update);
    }
}
