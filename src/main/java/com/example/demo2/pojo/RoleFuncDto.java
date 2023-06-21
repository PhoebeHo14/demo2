package com.example.demo2.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleFuncDto {
    String funcNameEn;
    String funcCode;
    String roleName;
    String roleCode;
    String sla;
    String canView;
    Integer active;

    public RoleFuncDto(String funcNameEn, String funcCode, String roleName, String roleCode, String sla, String canView, Integer active) {
        this.funcNameEn = funcNameEn;
        this.funcCode = funcCode;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.sla = sla;
        this.canView = canView;
        this.active = active;
    }
}
