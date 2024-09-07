package com.poonia.project.unber.uberApp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
