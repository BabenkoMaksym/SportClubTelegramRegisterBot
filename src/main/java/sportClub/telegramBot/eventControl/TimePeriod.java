package sportClub.telegramBot.eventControl;

import lombok.Data;

import java.util.Date;
@Data

public class TimePeriod {

   private Date start;
   private Date end;
   private boolean occupied;
}
