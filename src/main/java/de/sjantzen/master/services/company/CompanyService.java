package de.sjantzen.master.services.company;

import de.sjantzen.master.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sJantzen on 09.01.2018.
 */
public interface CompanyService{

    Company findCompanyByName(String name);

    void saveCompany(Company company);

    Company getCompanyOfCurrentAccount();
}
