package de.sjantzen.master.services.web;

import de.sjantzen.master.model.User;
import de.sjantzen.master.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/frontend/user")
public class UserWebService {

    private static final Logger LOG = LoggerFactory.getLogger(UserWebService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User signup(@RequestBody User user) {
        LOG.info("User to signup: " + user.toString());

        if (userService.findUserByEmail(user.getEmail()) != null) {
            LOG.info("A User with the given email address: " + user.getEmail() + " already exists.");
            // an user with this email address already exists
            return null;
        } else {
            // create new user
            LOG.info("Create a new user.");
            return userService.saveUser(user);
        }
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean login(@RequestBody User user) {
        LOG.info("Login for User: " + user.toString());

        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {

            if (bCryptPasswordEncoder.matches(user.getPassword(), userExists.getPassword())) {
                return true;
            }
            LOG.info("Wrong password for user with email: " + user.getEmail());
            return false;
        }
        LOG.info("User with given email: " + user.getEmail() + " doesn't exist.");
        return false;
    }
}