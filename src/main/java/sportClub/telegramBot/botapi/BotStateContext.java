package sportClub.telegramBot.botapi;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private Map<BotState, InputHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputHandler> inputHandlerList){
        inputHandlerList.forEach(handler -> messageHandlers.put(handler.getHandleName(), handler));
    }

    public SendMessage processInput(BotState state, Message message){
        InputHandler handler = messageHandlers.get(state);
        return handler.handle(message);
    }
}
