package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by v on 28/01/16.
 */
@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcUserRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public User save(User user) {
        jdbc.update(
            "insert into users (id, username, password, email, firstname, lastname ) values (?, ?, ?, ?, ?, ?)",
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName()
            );
        return user; // TODO: Determine value for id
    }

    public User findByUsername(String username) {
        try {
            return jdbc.queryForObject(
                    "select id, username, password, firstname, lastname, email from users where username=?",
                    new UserRowMapper(),
                    username);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"));
        }
    }

}

