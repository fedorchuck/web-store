package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.Commodity;

import java.util.List;
import java.util.Map;

/**
 * Created by v on 12/02/16.
 */
public interface CommodityRepository {
    Commodity save(Commodity commodity);
    List<Commodity> findByName(String name);
    List<Commodity> findByCost(Double cost);
    List<Commodity> findByManufacturer(String manufacturer);
    List<Commodity> all();
}
