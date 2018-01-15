package de.sjantzen.master.menu;

import de.sjantzen.master.constants.Size;
import de.sjantzen.master.model.Account;
import de.sjantzen.master.model.Category;
import de.sjantzen.master.model.Company;
import de.sjantzen.master.model.Product;
import de.sjantzen.master.repositories.CategoryRepository;
import de.sjantzen.master.repositories.CompanyRepository;
import de.sjantzen.master.repositories.ProductRepository;
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

/**
 * Created by sJantzen on 21.12.2017.
 */
@Controller
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/menu/saveProduct")
    public String saveProduct(@ModelAttribute Product product) {

        productRepository.save(product);

        return "menu/showMenu";
    }

    @RequestMapping(value = "/menu/deleteProduct/{productId}", method = RequestMethod.GET)
    public String deleteProduct(Model model, @PathVariable("productId")long productId) {
        LOG.info("Delete product with id: " + productId);

        if (productId > 0) {
            productRepository.delete(productId);
            LOG.info("Deleted product with id: " + productId);
        }

        final Company company = companyService.getCompanyOfCurrentAccount();

        model.addAttribute("company", company);
        model.addAttribute("sizes", Size.values());
        model.addAttribute("product", new Product());
        return "menu/fragments/products-overview :: productsOverview";
    }


}

