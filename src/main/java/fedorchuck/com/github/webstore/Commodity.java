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

package fedorchuck.com.github.webstore;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Commodity {

    private int id;

    private UUID commodity_id;

    @NotNull
    @Size(min = 2, max = 60, message="{name.size}")
    private String name;

    @NotNull
    @Size(min = 2, max = 60, message="{manufacturer.size}")
    private String manufacturer;

    /*@NotBlank
    //TODO: find or write correct input validation.
    @Size(min = 0, max = 2147483647, message="{cost.size}")*/
    private Double cost;

    private Integer quantity;

    private Boolean sell_out;

    @NotNull
    @Size(min = 0, max = 120000, message="{characteristics.size}")
    private String characteristics;

    @Size(min = 0, max = 1200, message="{description.size}")
    private String description;

    private String category;

    private UUID addedBy;

    public Commodity() { }

    public Commodity(
            String name, String manufacturer, Double cost, Integer quantity, Boolean sell_out,
            String characteristics, String description, String category, UUID addedBy) {
        this.commodity_id = UUID.randomUUID();
        this.name = name;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.quantity = quantity;
        this.sell_out = sell_out;
        this.characteristics = characteristics;
        this.description = description;
        this.category = category;
        this.addedBy = addedBy;
    }

    public Commodity(
            UUID commodity_id, String name, String manufacturer, Double cost, Integer quantity,
            Boolean sell_out, String characteristics, String description, String category, UUID addedBy) {
        this.commodity_id = commodity_id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.quantity = quantity;
        this.sell_out = sell_out;
        this.characteristics = characteristics;
        this.description = description;
        this.category = category;
        this.addedBy = addedBy;
    }

    public Commodity(
            int id, UUID commodity_id, String name, String manufacturer, Double cost, Integer quantity,
            Boolean sell_out, String characteristics, String description, String category, UUID addedBy) {
        this.id = id;
        this.commodity_id = commodity_id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.quantity = quantity;
        this.sell_out = sell_out;
        this.characteristics = characteristics;
        this.description = description;
        this.category = category;
        this.addedBy = addedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(UUID commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getSell_out() {
        return sell_out;
    }

    public void setSell_out(Boolean sell_out) {
        this.sell_out = sell_out;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UUID getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UUID addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", commodity_id='" + commodity_id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", cost='" + cost + '\'' +
                ", quantity='" + quantity + '\'' +
                ", sell_out='" + sell_out + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", addedBy='" + addedBy + '\'' +
                '}';
    }
}
