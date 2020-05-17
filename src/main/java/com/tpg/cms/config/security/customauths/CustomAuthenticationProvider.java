package com.tpg.cms.config.security.customauths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(name);


        if (!userDetails.getPassword().equals(password)) {
            logger.error("Authentication failed for user = " + name);
            throw new BadCredentialsException("Authentication failed for user = " + name);
        }

        // find out the exited users
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//        grantedAuthorities.add(userDetails.getAuthorities());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password,
                userDetails.getAuthorities());

        logger.info("Succesful Authentication with user = " + name);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
