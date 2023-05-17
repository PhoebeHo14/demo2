package com.example.demo2.model.first.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "work_time")
public class FirstWorkTimeDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer accountId;
    private float workTime;
    private LocalDate checkInDate;

}

