package com.example.demo2.model.second.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "work_time")
@Getter
@Setter
public class SecondWorkTimeDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "work_minute")
    private Integer workMinute;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

}
