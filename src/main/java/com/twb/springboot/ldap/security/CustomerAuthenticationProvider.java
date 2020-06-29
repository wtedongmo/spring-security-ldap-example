package com.twb.springboot.ldap.security;

import com.twb.springboot.ldap.db.User;
import com.twb.springboot.ldap.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    private ActiveDirectoryLdapAuthenticationProvider delegate; // add additional methods to initialize delegate during your configuration

    @Override
    public Authentication authenticate(Authentication auth) throws
            AuthenticationException {
        String username = auth.getPrincipal().toString();
        String password = auth.getCredentials().toString();

        User user = userRepository.findByLogin(username);
        if(user!=null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean passVerified = passwordEncoder.matches(password, user.getPassword());
            if(passVerified){
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(user));
            }
        }
//        Authentication authentication = delegate.authenticate(auth);
//        additionalChecks(authentication);
//        return auth;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ANONYMOUS"));
        return new AnonymousAuthenticationToken(username, password, authorities);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		for(UserProfile userProfile : user.getUserProfiles()){
//			System.out.println("UserProfile : "+userProfile);
//			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
//		}
        System.out.print("authorities :"+authorities);
        return authorities;
    }
}