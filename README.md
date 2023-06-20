SportClubRegisterTelegramBot

Set up ngrok(In our case, ngrok is a web hook)
1. Sign up in https://dashboard.ngrok.com/login and get authtoken for free.
2. Download ngrok on your machine: https://ngrok.com/download (There is all information about installation, even for Docker).
3. Set an authtoken to ngrok https://ngrok.com/docs/secure-tunnels/ngrok-agent/tunnel-authtokens/.
4. Start ngrok with this command: ngrok http <PORT>. In our case, PORT is 5000.
5. Copy provided Frowarding url.

Set up telegram bot application.
1. Open Environment variables of your application (Intellij IDEA, Heroku etc.).
2. Set these variables:
    TOKEN - the bot's token what BotFather gave you.
    USERNAME - the bot's username what you set in BotFather.
    WEB_HOOK_PATH - paste the Frowarding url.
3. Run your application server.
