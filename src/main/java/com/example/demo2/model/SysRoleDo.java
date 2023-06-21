package com.example.demo2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SYS_ROLE")
@Getter
@Setter
public class SysRoleDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ROLE_CODE", nullable = false)
    private String roleCode;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "DISP_SEQ", nullable = false)
    private int dispSeq;

    @Column(name = "ACTIVE_IND", nullable = false, columnDefinition = "varchar(1) DEFAULT 'Y'")
    private String activeInd;

    @Column(name = "INACTIVE_DATE")
    private LocalDateTime inactiveDate;

    @Column(name = "ROLE_TYPE")
    private String roleType;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_DATE", nullable = false)
    private LocalDateTime lastUpdatedDate;
}

