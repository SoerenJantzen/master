package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
