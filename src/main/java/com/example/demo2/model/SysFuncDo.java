package com.example.demo2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYS_FUNC")
@Getter
@Setter
public class SysFuncDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FUNC_CODE", nullable = false)
    private String funcCode;

    @Column(name = "UI_SREF")
    private String uiSref;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "LEVEL_1_POS")
    private Integer level1Pos;

    @Column(name = "LEVEL_2_POS")
    private Integer level2Pos;

    @Column(name = "FUNC_URL")
    private String funcUrl;

    @Column(name = "FUNC_NAME_EN")
    private String funcNameEn;

    @Column(name = "FUNC_NAME_ZH_CN")
    private String funcNameZhCn;

    @Column(name = "FUNC_NAME_ZH_TW")
    private String funcNameZhTw;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_DATE", nullable = false)
    private Date lastUpdatedDate;

    @Column(name = "SLA_SERVICE")
    private String slaService;
}


