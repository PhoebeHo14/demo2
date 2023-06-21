package com.example.demo2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SYS_REVAMP_FUNC")
@Getter
@Setter
public class SysRevampFuncDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PARENT_FUNC_ID", nullable = false, columnDefinition = "int(11) DEFAULT 0")
    private int parentFuncId;

    @Column(name = "POSITION", nullable = false)
    private int position;

    @Column(name = "FUNC_CODE", nullable = false)
    private String funcCode;

    @Column(name = "SLA_SERVICE")
    private String slaService;

    @Column(name = "FUNC_NAME_EN", nullable = false)
    private String funcNameEn;

    @Column(name = "FUNC_NAME_CN", nullable = false)
    private String funcNameCn;

    @Column(name = "FUNC_NAME_TW", nullable = false)
    private String funcNameTw;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "LAST_UPDATED_DATE", nullable = false)
    private LocalDateTime lastUpdatedDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
}

