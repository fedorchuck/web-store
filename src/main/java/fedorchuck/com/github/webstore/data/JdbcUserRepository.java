package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.User;
import org.springframework.beans.factory.annotation.Autowired;
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
      /*  jdbc.update(
                "insert into Spitter (username, password, first_name, last_name, email)" +
                        " values (?, ?, ?, ?, ?)",
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());*/
        return user; // TODO: Determine value for id
    }

    public User findByUsername(String username) {
        return new User();/*jdbc.queryForObject(
                "select id, username, null, first_name, last_name, email from Spitter where username=?",
                new SpitterRowMapper(),
                username);*/
    }

    private static class SpitterRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User();/*
                    rs.getInt("id"), //TODO: check this place.
                    rs.getString("username"),
                    null,
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"));*/
        }
    }

}

