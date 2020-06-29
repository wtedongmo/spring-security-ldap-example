package com.twb.springboot.ldap.security;

import com.twb.springboot.ldap.db.User;
import com.twb.springboot.ldap.db.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("myAuthPopulator")
public class MyAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    @Autowired
    private UserRepository userRepository;
    static final Logger LOG = LoggerFactory.getLogger(MyAuthoritiesPopulator.class);

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        try{
            User user = userRepository.findByLogin(username);
            if (user==null)
                LOG.error("Threw exception in MyAuthoritiesPopulator::getGrantedAuthorities : User doesn't exist into ATS database" );
//            else{
//                for(UserRole userRole : user.getUserRole()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//                }
                return authorities;
//            }
        }catch(Exception e){
            LOG.error("Threw exception in MyAuthoritiesPopulator::getGrantedAuthorities : " ); }
        return authorities;
    }
}

