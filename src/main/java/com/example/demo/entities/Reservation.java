package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;



@Data
@Table(name="forms")
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone_number;
    private int nb_adults;
    private int nb_children;
    private String check_in_date;
    private String check_out_date;
    private String room_preference;
    private String comments;
    private String state;

}
