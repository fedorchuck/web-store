/*
 * The GNU General Public Licence
 *
 * Copyright (c) 2016 by Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>
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
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 */

package com.github.fedorchuck.webstore.domainmodels;

import java.util.List;
import java.util.Random;

public class Order {
    private Integer order_id;
    private List<Commodity> basket;
    private Integer status;
    private String datefor;

    public Order(Integer order_id) {
        this.order_id = order_id;
        this.status = 5;
        this.datefor = "unknown";
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public List<Commodity> getBasket() {
        return basket;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDatefor() {
        return datefor;
    }

    public void setBasket(List<Commodity> basket) {
        this.basket = basket;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDatefor(String datefor) {
        this.datefor = datefor;
    }

    @Deprecated
    public Order() {
        order_id = new Random().nextInt();
    }

    public Order(Integer order_id, List<Commodity> basket, Integer status, String datefor) {
        this.order_id = order_id;
        this.basket = basket;
        this.status = status;
        this.datefor = datefor;
    }

    @Override
    public String toString() {
        return ":{" +
                "order_id=" + order_id +
                ", basket=" + basket +
                ", status=" + status +
                ", datefor='" + datefor + '\'' +
                "}\n";
    }
}
