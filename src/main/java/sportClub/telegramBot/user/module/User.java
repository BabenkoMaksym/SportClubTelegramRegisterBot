package sportClub.telegramBot.user.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users", schema = "sport_club")
public class User {

    @Id
    Long id;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

}
