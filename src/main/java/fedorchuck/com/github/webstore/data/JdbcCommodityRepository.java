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

import fedorchuck.com.github.webstore.Category;
import fedorchuck.com.github.webstore.Commodity;
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
public class JdbcCommodityRepository implements CommodityRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcCommodityRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Commodity save(Commodity commodity) {
        jdbc.queryForList(
                "insert into goods (commodity_id, name, manufacturer, cost, quantity, " +
                "sell_out, characteristics, description, category, addedBy) " +
                "values (?,?,?,cast(? as NUMERIC ),?,?,?,?,?,?) RETURNING id",
                commodity.getCommodity_id(),
                commodity.getName(),
                commodity.getManufacturer(),
                commodity.getCost(),
                commodity.getQuantity(),
                commodity.getSell_out(),
                commodity.getCharacteristics(),
                commodity.getDescription(),
                commodity.getCategory(),
                commodity.getAddedBy()
        );
        return commodity;
    }

    @Override
    public Commodity findByCommodity_id(UUID commodity_id) {
        try {
            return jdbc.queryForObject(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where commodity_id=?",
                    new CommodityRowMapper(),
                    commodity_id);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Commodity> findByName(String name) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cast(cost as double precision), quantity, " +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where name=?",
                    new CommodityRowMapper(),
                    name);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Commodity> findByManufacturer(String manufacturer) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where manufacturer=?",
                    new CommodityRowMapper(),
                    manufacturer);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Commodity> findByCost(Double cost) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where cost=?",
                    new CommodityRowMapper(),
                    cost);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Commodity> findByQuantity(Integer quantity) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where quantity=?",
                    new CommodityRowMapper(),
                    quantity);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Commodity> findBySell_out(Boolean sell_out) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where sell_out=?",
                    new CommodityRowMapper(),
                    sell_out);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Commodity> findByCategory(String category) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where category=?",
                    new CommodityRowMapper(),
                    category);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Category> findByCategory() {
        try {
            return jdbc.query(
                    "SELECT DISTINCT category FROM goods;",
                    new CategoryRowMapper()
            );
        } catch (EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public List<Commodity> findByAddedBy(UUID addedBy) {
        try {
            return jdbc.query(
                    "select id, commodity_id, name, manufacturer, cost, quantity," +
                    "sell_out, characteristics, description, category, addedBy from goods " +
                    "where addedBy=?",
                    new CommodityRowMapper(),
                    addedBy);
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

    @Override
    public Boolean deleteByCommodity_id(UUID commodity_id) {
        try {
            jdbc.update(
            "DELETE from goods where commodity_id=?",
            commodity_id);
            return true;
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    private static class CommodityRowMapper implements RowMapper<Commodity> {
        public Commodity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Commodity(
                    rs.getInt("id"),
                    UUID.fromString(rs.getString("commodity_id")),
                    rs.getString("name"),
                    rs.getString("manufacturer"),
                    rs.getDouble("cost"),
                    rs.getInt("quantity"),
                    rs.getBoolean("sell_out"),
                    rs.getString("characteristics"),
                    rs.getString("description"),
                    rs.getString("category"),
                    UUID.fromString(rs.getString("addedBy"))
            );
        }
    }

    private static class CategoryRowMapper implements RowMapper<Category> {
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Category(
                    rs.getString("category")
            );
        }
    }
}
