package de.sjantzen.master.menu;

import de.sjantzen.master.constants.Size;
import de.sjantzen.master.model.Address;
import de.sjantzen.master.model.Category;
import de.sjantzen.master.model.Company;
import de.sjantzen.master.model.Product;
import de.sjantzen.master.repositories.AddressRepository;
import de.sjantzen.master.repositories.CategoryRepository;
import de.sjantzen.master.repositories.CompanyRepository;
import de.sjantzen.master.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sJantzen on 03.12.2017.
 */
@Controller
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/menu")
    public String showMenu() {
        LOG.debug("Show menu overview.");


        return "menu/showMenu";
    }

    @RequestMapping(value = "/menu/updateContent/{content}", method = RequestMethod.GET)
    public String updateContent(Model model, @PathVariable("content") String content) {

        LOG.debug("Update main content of menu.");

        final Company company = companyRepository.findOne(6L);

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
                final Company company = companyRepository.findOne(6L);
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

    @RequestMapping("/init/dummyData")
    public String initDbData() {
        LOG.info("Initialize dummy datas.");

        final Product prod1 = new Product("Cola", "Leckere Coca Cola und nicht die Pepsi kacke.", BigDecimal.valueOf(3));
        final Product prod2 = new Product("Fanta", "Und zwar die leckere mit Mandarinengeschmack.", BigDecimal.valueOf(3));
        final Product prod3 = new Product("Sprite", "Hab ich nie gemocht.", BigDecimal.valueOf(3));

        final Product prod4 = new Product("Bruschetta", "Die beste Vorspeise, die diese Welt zu bieten hat.", BigDecimal.valueOf(4));

        final Product prod5 = new Product("Saltimbocca", "Schön leckeres Fleisch mit Salbei und Beilage.", BigDecimal.valueOf(14));

        productRepository.save(prod1);
        productRepository.save(prod2);
        productRepository.save(prod3);
        productRepository.save(prod4);
        productRepository.save(prod5);

        final Category cat1 = new Category("Getränke", "Ganz viele, ganz tolle Getränke.", true, new HashSet<>(Arrays.asList(prod1, prod2, prod3)));
        final Category cat2 = new Category("Vorspeise", "Kleine, feine Snacks zum Anfüttern.", true, new HashSet<>(Arrays.asList(prod4)));
        final Category cat3 = new Category("Hauptgerichte", "Deftig, fette Sattmachen. Mit viel Fleesch und Gemiese.", true, new HashSet<>(Arrays.asList(prod5)));

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);

        final Set<Category> categories = new HashSet<>();
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);

        Address address = new Address("Jenny-Lind-Str. 22", "13189", "Berlin");
        addressRepository.save(address);

        final Company company = new Company("Sönni Corp.", address, null, categories);
        companyRepository.save(company);

        prod1.setCategory(cat1);
        prod2.setCategory(cat1);
        prod3.setCategory(cat1);
        prod4.setCategory(cat2);
        prod5.setCategory(cat3);

        productRepository.save(prod1);
        productRepository.save(prod2);
        productRepository.save(prod3);
        productRepository.save(prod4);
        productRepository.save(prod5);

        cat1.setCompany(company);
        cat2.setCompany(company);
        cat3.setCompany(company);

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);

        return "Dummy data were initialized.";
    }
}