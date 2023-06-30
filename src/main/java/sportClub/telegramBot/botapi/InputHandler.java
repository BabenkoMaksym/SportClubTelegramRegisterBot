package sportClub.telegramBot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputHandler {

    SendMessage handle(Message message);

    BotState getHandleName();
}
