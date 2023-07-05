SportClubRegisterTelegramBot

Set up ngrok(In our case, ngrok is a web hook)
1. Sign up in https://dashboard.ngrok.com/login and get authtoken for free.
2. Download ngrok on your machine: https://ngrok.com/download (There is all information about installation, even for Docker).
3. Set an authtoken to ngrok https://ngrok.com/docs/secure-tunnels/ngrok-agent/tunnel-authtokens/.
4. Start ngrok with this command: ngrok http <PORT>. In our case, PORT is 5000.
5. Copy provided Frowarding url.

Set up telegram bot application
1. Open Environment variables of your application (Intellij IDEA, Heroku etc.).
2. Set these variables:
    TOKEN - the bot's token what BotFather gave you.
    USERNAME - the bot's username what you set in BotFather.
    WEB_HOOK_PATH - paste the Frowarding url.
3. Run your application server.

Set up api.telegram WebHook url
1. Paste this in Google promp to set WebHook url: https://api.telegram.org/bot<TOKEN>/setWebhook?url=<WEB_HOOK_PATH>/
2. To find out whether the url was set, paste this: https://api.telegram.org/bot<TOKEN>/getWebhookInfo/

#Code description
* TelegramBotApplication - Configuration class, what triggers auto-configuration and component scan. Is an entry point.

* BotController - Receives objects of Update class by http requests from WebHook, resend updates to TelegramBot onWebhookUpdateReceived method.

* ApplicationConfiguration - Declares @Bean methods

* TelegramBot - Telegram Bot configuration class. Stores token, bot username, web hook url and TelegramFacade object. Resend updates to TelegramFacade and returns received BotApiMethod<?>.

* TelegramFacade - A hub class what gets messages and handles them.

* BotStateContext - Stores all handlers for bot's states.

* BotState - Enum of bot's states.

* InputHandler - Interface for a received message. Has two abstract methods, handle() - handles message and build response message, getHandlerName - gets BotState of the handler class.

* ReplyMessageService - Service that retrieves localized messages using a message source and provides methods to get messages and set the locale dynamically.

#Files
* messages_en_UK.properties - property file with messages in English.
