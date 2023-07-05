package sportClub.telegramBot.user.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sportClub.telegramBot.user.module.User;

import java.util.Optional;

@Repository
public class UserJdbcRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Optional<User> findUserById(long id){
        String query = "SELECT * FROM sport_club.users WHERE id=:id";
        try {
            return template.queryForObject(query,
                    new MapSqlParameterSource().addValue("id", id),
                    (rs, rowNum) -> Optional.of(User.builder()
                            .id(rs.getLong("id"))
                            .phoneNumber(rs.getString("phone_number"))
                            .firstname(rs.getString("firstname"))
                            .lastname(rs.getString("lastname")).build()));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
