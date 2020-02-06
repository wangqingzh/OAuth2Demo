package com.example.xxx.security;

import com.example.xxx.domain.User;
import com.example.xxx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户服务 即用户存储在数据库中
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null){
            throw  new UsernameNotFoundException("用户未找到");
        }
        user.setRoles(userRepository.getUserRolesByUid(user.getId()));
        return user;
    }
}
