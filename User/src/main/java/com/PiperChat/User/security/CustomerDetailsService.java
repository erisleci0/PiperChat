package com.PiperChat.User.security;

import com.PiperChat.User.user.UserEntity;
import com.PiperChat.User.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomerDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Collection<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().getName()));
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
