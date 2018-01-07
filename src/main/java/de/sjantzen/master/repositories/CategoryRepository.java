package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sJantzen on 19.12.2017.
 */
public interface CategoryRepository  extends CrudRepository<Category, Long> {

    @Query(value = "SELECT * FROM category WHERE company_id = :companyId", nativeQuery = true)
    List<Category> findByCompanyId(@Param("companyId") long companyId);
}
