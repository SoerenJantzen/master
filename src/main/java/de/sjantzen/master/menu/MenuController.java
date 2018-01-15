package de.sjantzen.master.menu;

import de.sjantzen.master.authentication.IAuthenticationFacade;
import de.sjantzen.master.constants.OrderStatus;
import de.sjantzen.master.constants.Size;
import de.sjantzen.master.model.*;
import de.sjantzen.master.repositories.*;
import de.sjantzen.master.services.account.AccountService;
import de.sjantzen.master.services.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sJantzen on 03.12.2017.
 */
@Controller
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/menu")
    public String showMenu() {
        LOG.debug("Show menu overview.");

        return "menu/showMenu";
    }

    @RequestMapping(value = "/menu/updateContent/{content}", method = RequestMethod.GET)
    public String updateContent(Model model, @PathVariable("content") String content) {

        LOG.debug("Update main content of menu.");

        Company company = companyService.getCompanyOfCurrentAccount();

        model.addAttribute("company", company);
        model.addAttribute("sizes", Size.values());

        switch (content) {
            case "categories":
                model.addAttribute("category", new Category());
                return "menu/fragments/category-overview :: categoryOverview";
            case "products":
                model.addAttribute("product", new Product());
                return "menu/fragments/products-overview :: productsOverview";
            case "additives":
                return "menu/fragments/additives-overview :: additivesOverview";
            default:
                LOG.info("Unknown content name.");
        }
        return "menu/fragments/category-overview :: categoryOverview";
    }

    @RequestMapping(value = "/menu/details/{type}/{id}", method = RequestMethod.GET)
    public String showDetails(Model model, @PathVariable("type") String type, @PathVariable("id") long id) {
        LOG.info("Show details for " + type + " with id: " + id);

        switch (type) {
            case "category":
                final Category category = categoryRepository.findOne(id);
                model.addAttribute("category", category);
                return "menu/fragments/category-details :: detailForm";
            case "product":

                final Product product = productRepository.findOne(id);
                final Company company = companyService.getCompanyOfCurrentAccount();
                model.addAttribute("product", product);
                model.addAttribute("sizes", Size.values());
                model.addAttribute("categories", company.getCategories());

                return "menu/fragments/product-details :: detailForm";
            case "additive":

                return "menu/fragments/additive-details :: detailForm";
            default:
                LOG.info("Unknown type.");
        }

        return "menu/fragments/category-details :: detailForm";
    }

}