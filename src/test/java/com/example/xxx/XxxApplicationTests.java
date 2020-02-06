package com.example.xxx;

import com.example.xxx.domain.Book;
import com.example.xxx.domain.Role;
import com.example.xxx.domain.User;
import com.example.xxx.repository.BookRepository;
import com.example.xxx.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XxxApplicationTests {

    public final static Logger log = LoggerFactory.getLogger(XxxApplicationTests.class);

    @Autowired
    BookRepository repository;


    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        log.info(new BCryptPasswordEncoder(10).encode("123"));
    }

}
