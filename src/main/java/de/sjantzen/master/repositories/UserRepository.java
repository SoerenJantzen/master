package de.sjantzen.master.repositories;

import de.sjantzen.master.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sJantzen on 19.12.2017.
 */
@Repository("userRepository")
public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
