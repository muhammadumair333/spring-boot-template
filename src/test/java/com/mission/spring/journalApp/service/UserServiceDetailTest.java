package com.mission.spring.journalApp.service;

import com.mission.spring.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class UserServiceDetailTest {

    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    private void setUp(){
        initMocks(this);
    }

   @Test
   void loadUserByUserNameTest(){
      /* com.mission.spring.journalApp.entity.User mockUser = new com.mission.spring.journalApp.entity.User("ali", "123");
       mockUser.setUserName("ali");
       mockUser.setPassword("123");
       mockUser.setRoles(new ArrayList<>());
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);
        UserDetails user = userDetailsServiceImp.loadUserByUsername("ali");
        Assertions.assertNotNull(user);*/

    }
}
