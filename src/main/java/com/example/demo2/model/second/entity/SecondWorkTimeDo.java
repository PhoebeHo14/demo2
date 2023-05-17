package com.example.demo2.model.second.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "work_time")
@Getter
@Setter
@Schema(name = "db2 工時")
public class SecondWorkTimeDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer accountId;
    private float workTime;
    private LocalDate checkInDate;

}
