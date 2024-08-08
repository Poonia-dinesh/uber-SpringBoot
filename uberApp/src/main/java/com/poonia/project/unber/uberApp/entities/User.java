package com.poonia.project.unber.uberApp.entities;
import com.poonia.project.unber.uberApp.entities.enums.Role;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String name;
    @Column(unique = true)
    private String email;
    private  String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
