package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by v on 12/02/16.
 */
@Repository
public class JdbcCommodityRepository implements CommodityRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcCommodityRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Commodity save(Commodity commodity) {
        jdbc.queryForList("insert into goods (name, manufacturer, cost, characteristics, description) " +
                        "values (?,?,cast(? as NUMERIC ),?,?) RETURNING id",
                commodity.getName(),
                commodity.getManufacturer(),
                commodity.getCost(),
                commodity.getCharacteristics(),
                commodity.getDescription()
        );
        return commodity;
    }

    @Override
    public List<Commodity> findByName(String name) {
        try {
            return jdbc.query("select id, name, manufacturer, cast(cost as double precision), characteristics, " +
                            "description from goods where name=?",
                    new CommodityRowMapper(),
                    name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Commodity> findByCost(Double cost) {
        try {
            return jdbc.query("select id, name, manufacturer, cost, characteristics, description from goods " +
                    "where cost=?",
                    new CommodityRowMapper(),
                    cost);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Commodity> findByManufacturer(String manufacturer) {
        try {
            return jdbc.query("select id, name, manufacturer, cost, characteristics, description from goods " +
                    "where manufacturer=?",
                    new CommodityRowMapper(),
                    manufacturer);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Commodity> all() {
        try {
            return jdbc.query("select * from goods", new CommodityRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    private static class CommodityRowMapper implements RowMapper<Commodity> {
        public Commodity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Commodity(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("manufacturer"),
                    rs.getDouble("cost"),
                    rs.getString("characteristics"),
                    rs.getString("description")
            );
        }
    }
}
