package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sJantzen on 09.01.2018.
 */
public interface AccountRepository  extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
}
