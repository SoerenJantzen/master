package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sJantzen on 19.12.2017.
 */
public interface ProductRepository  extends CrudRepository<Product, Long> {
}
