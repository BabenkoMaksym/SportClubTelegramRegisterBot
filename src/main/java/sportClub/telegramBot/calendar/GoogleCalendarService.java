package sportClub.telegramBot.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "Sport Club";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    private final Calendar calendar;

    public GoogleCalendarService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String getAvailableSlots(String calendarId) throws IOException {
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = startDateTime.plusDays(30); // Adjust the duration as needed

        DateTime start = DateTime.parseRfc3339(String.valueOf(startDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        DateTime end = DateTime.parseRfc3339(String.valueOf(endDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        Events events = calendar.events().list(calendarId)
                .setTimeMin(start)
                .setTimeMax(end)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();
        StringBuilder slotsBuilder = new StringBuilder();
        for (Event event : items) {
            DateTime eventStart = event.getStart().getDateTime();
            DateTime eventEnd = event.getEnd().getDateTime();
            if (eventStart != null && eventEnd != null) {
                slotsBuilder.append("Start: ").append(eventStart).append(", End: ").append(eventEnd).append("\n");
            }
        }
        return slotsBuilder.toString();
    }

    public void bookFacility(String calendarId, LocalDateTime startDateTime, LocalDateTime endDateTime) throws IOException {
        Event event = new Event();
        event.setSummary("Booked Facility");
        event.setDescription("Facility booking");

        DateTime start = DateTime.parseRfc3339(String.valueOf(startDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        DateTime end = DateTime.parseRfc3339(String.valueOf(endDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        EventDateTime eventStart = new EventDateTime().setDateTime(start);
        EventDateTime eventEnd = new EventDateTime().setDateTime(end);

        event.setStart(eventStart);
        event.setEnd(eventEnd);

        calendar.events().insert(calendarId, event).execute();
    }

    private Credential getCredentials(HttpTransport httpTransport) throws IOException {
        InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}