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

import fedorchuck.com.github.webstore.Commodity;

import java.util.List;
import java.util.UUID;

public interface CommodityRepository {
    Commodity save(Commodity commodity);
    Commodity findByCommodity_id(UUID commodity_id);
    List<Commodity> findByName(String name);
    List<Commodity> findByManufacturer(String manufacturer);
    List<Commodity> findByCost(Double cost);
    List<Commodity> findByQuantity(Integer quantity);
    List<Commodity> findBySell_out(Boolean sell_out);
    List<Commodity> findByCategory(String category);
    List<Commodity> findByAddedBy(UUID addedBy);
    List<Commodity> all();

    Boolean deleteByCommodity_id(UUID commodity_id);
}
