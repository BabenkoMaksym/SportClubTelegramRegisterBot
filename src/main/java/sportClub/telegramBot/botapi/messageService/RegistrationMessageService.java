package sportClub.telegramBot.botapi.messageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationMessageService {

    @Autowired
    ReplyMessageService messageService;

    public SendMessage getRegistrationMenuService(long chatId, String textMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = getRegistrationMenuKeyboard();
        return createMessageWithKeyboard(chatId, messageService.getMessage(textMessage), replyKeyboardMarkup);
    }

    private ReplyKeyboardMarkup getRegistrationMenuKeyboard(){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow rowButtons = new KeyboardRow();
        KeyboardButton askPhoneNumber = new KeyboardButton(messageService.getMessage("send_phone_button_text"));
        askPhoneNumber.setRequestContact(true);
        rowButtons.add(askPhoneNumber);
        keyboard.add(rowButtons);

        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(long chatId,
                                                  String textMessage,
                                                  ReplyKeyboardMarkup replyKeyboardMarkup){
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableHtml(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
