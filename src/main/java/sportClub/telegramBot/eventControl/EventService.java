package sportClub.telegramBot.eventControl;

import org.springframework.stereotype.Component;

@Component
public class EventService {
    public boolean bookTime(TimePeriod timePeriod){

        return checkTime(timePeriod);
    }

    public boolean checkTime(TimePeriod timePeriod){
        return true;
    }


}
