package com.example.demo2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "SYS_ROLE_FUNC")
public class SysRoleFuncDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "FUNC_ID")
    private Integer funcId;

    @Column(name = "CAN_SELECT")
    private String canSelect;

    @Column(name = "CAN_INSERT")
    private String canInsert;

    @Column(name = "CAN_UPDATE")
    private String canUpdate;

    @Column(name = "CAN_DELETE")
    private String canDelete;

    @Column(name = "CAN_AUDIT")
    private String canAudit;

    @Column(name = "CAN_VIEW")
    private String canView;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_DATE")
    private LocalDateTime lastUpdatedDate;
}

