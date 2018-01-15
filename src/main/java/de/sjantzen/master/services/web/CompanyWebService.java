package de.sjantzen.master.services.web;

import de.sjantzen.master.model.Company;
import de.sjantzen.master.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyWebService {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyWebService.class);

    @Autowired
    private CompanyRepository companyRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/frontend/company/allForListView", method = RequestMethod.GET)
    public @ResponseBody List<Company> getAllCompaniesForListView() {
        List<Company> rv = new ArrayList<>();

        companyRepository.findAll().iterator().forEachRemaining(rv::add);

        return rv;
    }
}
