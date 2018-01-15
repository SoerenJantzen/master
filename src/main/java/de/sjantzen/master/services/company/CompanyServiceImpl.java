package de.sjantzen.master.services.company;

import de.sjantzen.master.model.Account;
import de.sjantzen.master.model.Company;
import de.sjantzen.master.repositories.CompanyRepository;
import de.sjantzen.master.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by sJantzen on 09.01.2018.
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Company findCompanyByName(final String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyOfCurrentAccount() {
        Company company = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Account account = accountService.findAccountByEmail(auth.getName());
        if (account != null) {
            company = account.getCompany();
        }
        else {
            company = new Company();
        }
        return company;
    }
}
