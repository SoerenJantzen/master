package de.sjantzen.master.services.web;

import de.sjantzen.master.model.Company;
import de.sjantzen.master.model.Orders;
import de.sjantzen.master.model.User;
import de.sjantzen.master.repositories.CompanyRepository;
import de.sjantzen.master.repositories.OrdersRepository;
import de.sjantzen.master.repositories.UserRepository;
import de.sjantzen.master.services.company.CompanyService;
import de.sjantzen.master.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/frontend/orders")
public class OrdersWebService {

    private static final Logger LOG = LoggerFactory.getLogger(OrdersWebService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Orders order(@RequestBody Orders orders) {
        LOG.info("Save order: " + orders.toString());
        LOG.info("Given Email: " + orders.getUserEmail() + "; Given CompanyId: " + orders.getCompanyId());

        User userExists = userService.findUserByEmail(orders.getUserEmail());
        if (userExists == null) {
            LOG.error("User with the given email: " + orders.getUserEmail() + " doesn't exists.");
            return null;
        }

        /*
        if (orders.getCompany() == null) {
            LOG.error("The given company to be ordered from is null.");
            return null;
        }
        */

        //Company companyExists = companyService.findCompanyByName(orders.getCompany().getName());
        Company companyExists = companyRepository.findOne(orders.getCompanyId());
        if (companyExists == null) {
            LOG.error("Company with the given name: " + orders.getCompany().getName() + " doesn't exists.");
            return null;
        }

        Orders rv = new Orders();
        // TODO generate a unique pick up number with 4 digits
        rv.setCompany(companyExists);
        rv.setDueDatetime(orders.getDueDatetime());
        rv.setUserEmail(orders.getUserEmail());
        rv.setProducts(orders.getProducts());
        rv.setPickUpNumber("007");
        rv.setOrderReceivedDatetime(new Date());

        LOG.info("AHA 1");
        rv = ordersRepository.save(rv);

        LOG.info("AHA 2");
        userExists.getOrders().add(rv);
        userRepository.save(userExists);

        LOG.info("AHA 3");
        rv.setUser(userExists);

        LOG.info("USER: " + userExists.getId() + "; " + userExists.getEmail() + "; " + userExists.getUsername());

        return ordersRepository.save(rv);
        //return rv;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/getOrdersForUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    List<Orders> getAllCompaniesForListView(@PathVariable("userId") long userId) {
        List<Orders> rv = new ArrayList<>();

        LOG.info("UserID: " + userId);

        rv.addAll(ordersRepository.findByUserId(userId));

        return rv;
    }

}
