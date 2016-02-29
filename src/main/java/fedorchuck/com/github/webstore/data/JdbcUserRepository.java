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
        jdbc.queryForList("insert into users (username, password, email, firstname, lastname ) " +
                        "values (?, ?, ?, ?, ?) RETURNING id",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
        return user;
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

    public Boolean deleteByUsername(String username) {
        try {
            jdbc.update(
                    "DELETE from users where username=?",
                    username);
            return true;
        }
        catch (EmptyResultDataAccessException e){
            return false;
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
                    rs.getString("email")
            );
        }
    }
}

