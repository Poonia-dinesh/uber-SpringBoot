package com.poonia.project.unber.uberApp.controllers;

import com.poonia.project.unber.uberApp.TestContainerConfiguration;
import com.poonia.project.unber.uberApp.dto.SignupDto;
import com.poonia.project.unber.uberApp.entities.User;
import com.poonia.project.unber.uberApp.entities.enums.Role;
import com.poonia.project.unber.uberApp.repositories.RiderRepository;
import com.poonia.project.unber.uberApp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
class AuthControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiderRepository riderRepository;
    private User user;

    @BeforeEach
    void setUserforEach()
    {
        user = new User();
        user.setId(1L);
        user.setEmail("test@Example.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.RIDER));
    }

    @Test
    void testSignUp_Success(){
        SignupDto signupDto = new SignupDto();
        signupDto.setEmail("test@Eample.com");
        signupDto.setName("Test name");
        signupDto.setPassword("password");

        webTestClient.post()
                .uri("/auth/signup")
                .bodyValue(signupDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.data.email").isEqualTo(signupDto.getEmail())
                .jsonPath("$.data.name").isEqualTo(signupDto.getName());

    }

}