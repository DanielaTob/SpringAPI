package com.project.marketapi.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarketUserDetailsService implements UserDetailsService {

    private static List<User> users = new ArrayList<>();

    public MarketUserDetailsService(){
        users.add(new User("daniela", "{noop}market", new ArrayList<>()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.stream().filter(user1 -> user1.getUsername().equals(username)).findAny();

        if (!user.isPresent()){
            throw new UsernameNotFoundException("User not found by name: " + username);
        }

        return user.get();
    }
}
