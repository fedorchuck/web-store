package fedorchuck.com.github.webstore.web;

import fedorchuck.com.github.webstore.data.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by v on 12/02/16.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private CommodityRepository commodityRepository;

    @Autowired
    public GoodsController(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }


}
