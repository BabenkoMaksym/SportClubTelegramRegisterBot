package sportClub.telegramBot.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ForbiddenClass {
    String reason() default "";
}