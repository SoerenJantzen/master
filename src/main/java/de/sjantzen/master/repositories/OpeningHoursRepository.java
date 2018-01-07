package de.sjantzen.master.repositories;

import de.sjantzen.master.model.OpeningHours;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sJantzen on 05.01.2018.
 */
public interface OpeningHoursRepository extends CrudRepository<OpeningHours, Long> {
}
