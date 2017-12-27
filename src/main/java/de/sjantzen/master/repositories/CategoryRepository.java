package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sJantzen on 19.12.2017.
 */
public interface CategoryRepository  extends CrudRepository<Category, Long> {
}
