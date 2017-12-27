package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Orders;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sJantzen on 19.12.2017.
 */
public interface OrdersRepository  extends CrudRepository<Orders, Long> {
}
