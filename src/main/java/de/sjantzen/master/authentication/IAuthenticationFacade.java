package de.sjantzen.master.authentication;

import org.springframework.security.core.Authentication;

/**
 * Created by sJantzen on 08.01.2018.
 */
public interface IAuthenticationFacade {

    Authentication getAuthentication();

}
