package de.sjantzen.master.services.web;

import de.sjantzen.master.model.Category;
import de.sjantzen.master.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryWebService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryWebService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/frontend/category/forCompanyId/{companyId}", method = RequestMethod.GET)
    public @ResponseBody
    List<Category> getAllCategoryForCompany(@PathVariable("companyId") long companyId) {
        List<Category> rv = new ArrayList<>();

        rv.addAll(categoryRepository.findByCompanyId(companyId));

        return rv;
    }

}
