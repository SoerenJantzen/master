package de.sjantzen.master.services;

import de.sjantzen.master.model.Company;
import de.sjantzen.master.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sJantzen on 30.12.2017.
 */
@Controller
public class CompanyService {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);

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
