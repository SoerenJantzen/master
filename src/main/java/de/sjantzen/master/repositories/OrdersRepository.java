package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by sJantzen on 19.12.2017.
 */
public interface OrdersRepository  extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(long userId);
}
