package sportClub.telegramBot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import sportClub.telegramBot.TelegramBot;

@Slf4j
@RestController
@RequestMapping("/")
public class BotController {

    @Autowired
    private TelegramBot telegramBot;

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        log.info(String.format("%s sent: %s", update.getMessage().getFrom().getFirstName(), update.getMessage().getText()));

        return telegramBot.onWebhookUpdateReceived(update);
    }

}
