package de.sjantzen.master.menu;

import de.sjantzen.master.constants.Size;
import de.sjantzen.master.model.Category;
import de.sjantzen.master.model.Company;
import de.sjantzen.master.model.Product;
import de.sjantzen.master.repositories.CategoryRepository;
import de.sjantzen.master.repositories.CompanyRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sJantzen on 20.12.2017.
 */
@Controller
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/menu/saveCategory")
    public String showMenu(@ModelAttribute Category category) {
        LOG.info("CAT ID: " + category.getId());
        LOG.info("CAT NAME: " + category.getName());
        LOG.info("CAT DESCRIPTION: " + category.getDescription());

        final Company company = companyRepository.findOne(6L);

        // Save new Category
        category.setCompany(company);
        categoryRepository.save(category);

        return "menu/showMenu";
    }

    @RequestMapping(value = "/menu/deleteCategory/{categoryId}", method = RequestMethod.GET)
    public String deleteCategory(Model model, @PathVariable("categoryId")long categoryId) {
        LOG.info("Delete category with id: " + categoryId);

        if (categoryId > 0) {
            categoryRepository.delete(categoryId);
            LOG.info("Deleted category with id: " + categoryId);
        }

        final Company company = companyRepository.findOne(6L);

        model.addAttribute("company", company);
        model.addAttribute("sizes", Size.values());
        model.addAttribute("category", new Category());
        return "menu/fragments/category-overview :: categoryOverview";
    }
}
