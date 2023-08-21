package sportClub.telegramBot;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sportClub.telegramBot.calendar.GoogleCalendarService;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Log4j2
@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) throws GeneralSecurityException, IOException {SpringApplication.run(TelegramBotApplication.class, args);


	}
}
