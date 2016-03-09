/*
 *  The GNU General Public Licence
 *
 *  Copyright (c) 2016 by Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 */

package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcUserRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User save(User user) {
        jdbc.queryForList("insert into users " +
                        "(username, password, email, firstname, lastname, role, user_id ) " +
                        "values (?, ?, ?, ?, ?, ?, ?) RETURNING id",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getUser_id()
        );
        return user;
    }

    @Override
    public User findByUsername(String username) {
        try {
            return jdbc.queryForObject(
                    "select id, username, password, firstname, lastname, email, role, user_id " +
                    "from users where username=?",
                    new UserRowMapper(),
                    username);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<User> findByUserRole(Integer userRole) {
        try {
            return jdbc.query(
                    "select id, username, password, firstname, lastname, email, role, user_id " +
                    "from users where role=?",
                    new UserRowMapper(),
                    userRole);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User findByUser_id(UUID user_id) {
        try {
            return jdbc.queryForObject(
                    "select id, username, password, firstname, lastname, email, role, user_id " +
                            "from users where user_id=?",
                    new UserRowMapper(),
                    user_id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
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

    @Override
    public Boolean deleteByUser_id(UUID user_id) {
        try {
            jdbc.update(
                    "DELETE from users where user_id=?",
                    user_id);
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
                    rs.getString("email"),
                    rs.getInt("role"),
                    UUID.fromString(rs.getString("user_id"))
            );
        }
    }
}

