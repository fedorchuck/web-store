package fedorchuck.com.github.webstore;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by v on 12/02/16.
 */
public class Commodity {

    private int id;

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

    @NotNull
    @Size(min = 0, max = 120000, message="{characteristics.size}")
    private String characteristics;

    @Size(min = 0, max = 1200, message="{description.size}")
    private String description;

    public Commodity() { }

    public Commodity(String name, String manufacturer, Double cost, String characteristics, String description) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.characteristics = characteristics;
        this.description = description;
    }

    public Commodity(int id, String name, String manufacturer, Double cost, String characteristics, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.characteristics = characteristics;
        this.description = description;
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

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "name", "manufacturer", "cost", "characteristics");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode("name", "manufacturer", "cost", "characteristics");
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", cost=" + cost +
                ", characteristics='" + characteristics + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
