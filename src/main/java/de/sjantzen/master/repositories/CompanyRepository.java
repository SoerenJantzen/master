package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sJantzen on 19.12.2017.
 */
public interface CompanyRepository  extends JpaRepository<Company, Long> {

    Company findByName(String name);

}
