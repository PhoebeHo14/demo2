package com.example.demo2.service;

import com.example.demo2.dao.SysRevampRoleFuncRepository;
import com.example.demo2.dao.SysRoleFuncRepository;
import com.example.demo2.model.SysRevampRoleFuncDo;
import com.example.demo2.model.SysRoleFuncDo;
import com.example.demo2.pojo.RoleFuncDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MergeFuncRoleService {
    private final SysRoleFuncRepository sysRoleFuncRepository;
    private final SysRevampRoleFuncRepository sysRevampRoleFuncRepository;

    public List<RoleFuncDto> mergeFuncRole(){
        List<RoleFuncDto> roleFuncList = sysRoleFuncRepository.findRoleFuncWithCriteria();

        for (RoleFuncDto roleFuncDto : roleFuncList){
            if (roleFuncDto.getSla().isEmpty()) {
                roleFuncDto.setSla("NULL");
            }

//            System.out.println("Function Name (EN): " + roleFuncDto.getFuncNameEn());
//            System.out.println("Function Code: " + roleFuncDto.getFuncCode());
//            System.out.println("Role Name: " + roleFuncDto.getRoleName());
//            System.out.println("Role Code: " + roleFuncDto.getRoleCode());
//            System.out.println("SLA: " + roleFuncDto.getSla());
//            System.out.println();

        }
        for (RoleFuncDto roleFuncDto : roleFuncList) {
            checkMergeFuncRole(roleFuncDto);
        }

        return roleFuncList;
    }

    public void checkMergeFuncRole(RoleFuncDto roleFuncDto) {
            String roleCode = roleFuncDto.getRoleCode();
            String funcCode = roleFuncDto.getFuncCode();

//             Check if roleId and funcId exist in SYS_ROLE_FUNC
            SysRoleFuncDo sysRoleFuncDo = sysRoleFuncRepository.findByRoleCodeAndFuncCode(roleCode, funcCode);
            boolean canViewSysRole = sysRoleFuncDo != null && sysRoleFuncDo.getCanView().equals("Y");

//             Check if roleId and funcId exist in SYS_REVAMP_ROLE_FUNC
            SysRevampRoleFuncDo sysRevampRoleFuncDo = sysRevampRoleFuncRepository.findByRoleCodeAndFuncCode(roleCode, funcCode);
            boolean activeSysRevampRole = sysRevampRoleFuncDo != null && sysRevampRoleFuncDo.getActive() == 1;

        System.out.println("Role Code: " + roleCode);
            System.out.println("Func Code: " + funcCode);
            System.out.println("SYS_ROLE_FUNC - canView: " + (canViewSysRole ? "Y" : "N"));
            System.out.println("SYS_REVAMP_ROLE_FUNC - active: " + (activeSysRevampRole ? "1" : "0"));
            System.out.println();
        }
    }
