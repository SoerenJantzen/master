package de.sjantzen.master.repositories;

import de.sjantzen.master.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sJantzen on 08.01.2018.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
