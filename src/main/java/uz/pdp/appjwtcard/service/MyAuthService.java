package uz.pdp.appjwtcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //biznes logika

        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("Alisher", passwordEncoder.encode("alish123"), new ArrayList<>()),
                new User("Bekzod", passwordEncoder.encode("bek123"), new ArrayList<>()),
                new User("Doston", passwordEncoder.encode("dossi123"), new ArrayList<>())
        ));

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("username yoki parol xato");
    }

}
