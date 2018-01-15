package de.sjantzen.master.orders;

import de.sjantzen.master.menu.MenuController;
import de.sjantzen.master.model.Account;
import de.sjantzen.master.model.Company;
import de.sjantzen.master.model.Orders;
import de.sjantzen.master.repositories.CompanyRepository;
import de.sjantzen.master.repositories.OrdersRepository;
import de.sjantzen.master.services.account.AccountService;
import de.sjantzen.master.services.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sJantzen on 16.12.2017.
 */
@Controller
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private OrdersRepository ordersRepository;

    @RequestMapping("/orders")
    public String showOrders(Model model) {

        Company company = companyService.getCompanyOfCurrentAccount();

        model.addAttribute("orders", company.getOrders());

        return "orders/showOrders";
    }

    @RequestMapping(value = "/orders/details/{orderId}", method = RequestMethod.GET)
    public String showOrderDetails(Model model, @PathVariable("orderId") long orderId) {

        Orders order = ordersRepository.findOne(orderId);

        model.addAttribute("order", order);

        return "orders/fragments/order-details :: orderDetails";
    }
}
