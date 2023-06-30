package sportClub.telegramBot.messageService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Locale;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyMessageService {

    @Autowired
    Locale locale;

    @Autowired
    MessageSource messageSource;

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, locale);
    }

    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }
}
