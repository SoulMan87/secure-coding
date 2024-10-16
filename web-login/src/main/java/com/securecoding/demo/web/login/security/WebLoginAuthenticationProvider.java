package com.securecoding.demo.web.login.security;

import com.securecoding.demo.web.login.dataaccess.entity.User;
import com.securecoding.demo.web.login.service.UserService;
import com.securecoding.demo.web.login.validator.InputValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class WebLoginAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final UserDetailsService userDetailsService;

    private final InputValidator inputValidator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName ();
        var password = (String) authentication.getCredentials ();

        inputValidator.isValidUsername (username);
        inputValidator.isValidPassword (password);

        Optional<User> user = userService.findUserByUsernameAndPassword (username, password);

        if (user.isEmpty ()) {
            throw new BadCredentialsException ("Invalid username or password");
        }

        var userDetails = userDetailsService.loadUserByUsername (user.get ().getUsername ());

        return new UsernamePasswordAuthenticationToken (userDetails, password, userDetails.getAuthorities ());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals (UsernamePasswordAuthenticationToken.class);
    }
}
