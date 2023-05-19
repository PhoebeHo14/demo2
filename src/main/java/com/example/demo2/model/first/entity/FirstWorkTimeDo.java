package com.example.demo2.model.first.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "work_time")
@Getter
@Setter
@Schema(name = "db1 工時")
public class FirstWorkTimeDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "work_time")
    private float workTime;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

}

