package com.example.demo2.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RoleFuncDto {
    private String funcNameEn;
    private String funcCode;
    private String roleName;
    private String roleCode;
    private String slaService;
//    private String canView;
//    private Integer active;

//    public RoleFuncDto(String funcNameEn, String funcCode, String roleName, String roleCode, String slaService, String canView, Integer active) {
//        this.funcNameEn = funcNameEn;
//        this.funcCode = funcCode;
//        this.roleName = roleName;
//        this.roleCode = roleCode;
//        this.slaService = slaService;
//        this.canView = canView;
//        this.active = active;
//    }
}
