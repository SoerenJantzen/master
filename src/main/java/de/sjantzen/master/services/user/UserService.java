package de.sjantzen.master.services.user;

import de.sjantzen.master.model.User;

/**
 * Created by sJantzen on 08.01.2018.
 */
public interface UserService {

    public User findUserByEmail(String email);

    public User findUserByUsername(String username);

    public User saveUser(User user);
}
