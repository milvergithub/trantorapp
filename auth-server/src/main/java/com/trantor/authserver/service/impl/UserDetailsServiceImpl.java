package com.trantor.authserver.service.impl;

import com.trantor.authserver.domain.User;
import com.trantor.authserver.repository.UserRepository;
import com.trantor.authserver.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username.trim());
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
