package sportClub.telegramBot.botapi.handlers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import sportClub.telegramBot.botapi.BotState;
import sportClub.telegramBot.botapi.InputHandler;
import sportClub.telegramBot.botapi.messageService.ReplyMessageService;
import sportClub.telegramBot.user.cashe.DataCash;
import sportClub.telegramBot.user.module.User;
import sportClub.telegramBot.user.service.UserService;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneNumberHandler implements InputHandler {

    @Autowired
    UserService userService;

    @Autowired
    DataCash dataCash;

    @Autowired
    ReplyMessageService messageService;

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        String phoneNumber;
        org.telegram.telegrambots.meta.api.objects.User user = message.getFrom();

        if((phoneNumber = message.getContact().getPhoneNumber()) != null) {
            userService.saveUser(User.builder()
                    .id(user.getId())
                    .phoneNumber(phoneNumber)
                    .firstname(user.getFirstName())
                    .lastname(user.getLastName()).build());
            dataCash.saveCurrentBotState(user.getId(), BotState.MENU_MAIN);
            sendMessage.setText(messageService.getMessage("registration_success"));
        } else
            sendMessage.setText(messageService.getMessage("registration_error"));

        return sendMessage;
    }

    @Override
    public BotState getHandleName() {
        return BotState.INPUT_PHONE_NUMBER;
    }
}
