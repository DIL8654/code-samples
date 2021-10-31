package io.dilankam.springsecurityjwt.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * adding user detail service by implementing the {@Link UserDetailsService} from Spring security core
 *
 *
 * @author DilankaM
 * @created 31/10/2021 - 10:22
 */



@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("user", "user",new ArrayList<>()); // using hardcoded user for the illustration purpose with no authorities
    }
}
