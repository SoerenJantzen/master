package de.sjantzen.master.orders;

import de.sjantzen.master.menu.MenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sJantzen on 16.12.2017.
 */
@Controller
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/orders")
    public String showOrders() {

        return "orders/showOrders";
    }
}
