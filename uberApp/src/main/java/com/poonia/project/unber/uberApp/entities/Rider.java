package com.poonia.project.unber.uberApp.entities;


import jakarta.persistence.*;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
