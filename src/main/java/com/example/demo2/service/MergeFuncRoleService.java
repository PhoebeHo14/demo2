package com.example.demo2.service;

import com.example.demo2.dao.*;
import com.example.demo2.model.*;
import com.example.demo2.pojo.MergedRoleFuncDto;
import com.example.demo2.pojo.RoleFuncDto;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MergeFuncRoleService {
    private final SysRoleFuncRepository sysRoleFuncRepository;
    private final SysRevampRoleFuncRepository sysRevampRoleFuncRepository;
    private final RoleFuncMapper roleFuncMapper;
    private final SysRevampFuncRepository sysRevampFuncRepository;
    private final SysFuncRepository sysFuncRepository;
    private final SysRoleRepository sysRoleRepository;

    public List<MergedRoleFuncDto> mergeFuncRole() {
//        List<RoleFuncDto> roleFuncList = roleFuncMapper.findRoleFuncWithCriteria();
        List<RoleFuncDto> roleFuncList = new ArrayList<>();
        //找到2.0的roleFunc
        List<SysRevampRoleFuncDo> revampRoleFuncDoList = sysRevampRoleFuncRepository.findByActive(1);

        for (SysRevampRoleFuncDo sysRevampRoleFuncDo : revampRoleFuncDoList) {
            RoleFuncDto roleFuncDto = new RoleFuncDto();

            Integer revampFuncId = sysRevampRoleFuncDo.getFuncId();

            Optional<SysRevampFuncDo> SysRevampFuncDo = sysRevampFuncRepository.findById(revampFuncId);

            String funcCode = SysRevampFuncDo.get().getFuncCode();
            SysFuncDo sysFuncDo = sysFuncRepository.findByFuncCode(funcCode);
            Integer roleId = sysRevampRoleFuncDo.getRoleId();

            if (sysFuncDo != null) {

                System.out.println("sysFuncDo.getId()" + sysFuncDo.getId());
                System.out.println("roleId" + roleId);

                List<SysRoleFuncDo> sysRoleFuncDoList = sysRoleFuncRepository.findByFuncIdAndRoleIdAndCanView(sysFuncDo.getId(), roleId, "Y");
                if (sysRoleFuncDoList != null) {
                    Optional<SysRoleDo> sysRoleDo = sysRoleRepository.findById(roleId);

                    if (sysRoleDo.isPresent()) {
                        roleFuncDto.setFuncNameEn(SysRevampFuncDo.get().getFuncNameEn());
                        roleFuncDto.setFuncCode(funcCode);
                        roleFuncDto.setRoleName(sysRoleDo.get().getRoleName());
                        roleFuncDto.setRoleCode(sysRoleDo.get().getRoleCode());
                        roleFuncDto.setSlaService(SysRevampFuncDo.get().getSlaService());

                        roleFuncList.add(roleFuncDto);
                    }
                }
            }
        }

        List<MergedRoleFuncDto> mergedRoleFuncList = new ArrayList<>();

        Map<String, MergedRoleFuncDto> mergedDataMap = new HashMap<>();

        for (RoleFuncDto roleFuncDto : roleFuncList) {
            if (StringUtils.isNullOrEmpty(roleFuncDto.getSlaService())) {
                roleFuncDto.setSlaService("NULL");
            }

//            checkMergeFuncRole(roleFuncDto);

            String funcKey = roleFuncDto.getFuncNameEn() + "(" + roleFuncDto.getFuncCode() + ")";
            String roleValue = roleFuncDto.getRoleName() + "(" + roleFuncDto.getRoleCode() + ")";

            if (mergedDataMap.containsKey(funcKey)) {
                MergedRoleFuncDto mergedRoleFuncDto = mergedDataMap.get(funcKey);
                mergedRoleFuncDto.setRole(mergedRoleFuncDto.getRole() + ", " + roleValue);
//                System.out.println("mergedRoleFuncDto: " + mergedRoleFuncDto.getFunc() + "//" + mergedRoleFuncDto.getRole() + "//" + mergedRoleFuncDto.getSla());
            } else {
                MergedRoleFuncDto mergedRoleFuncDto = new MergedRoleFuncDto();
                mergedRoleFuncDto.setFunc(funcKey);
                mergedRoleFuncDto.setRole(roleValue);
                mergedRoleFuncDto.setSla(roleFuncDto.getSlaService());
                mergedDataMap.put(funcKey, mergedRoleFuncDto);
//                System.out.println("mergedRoleFuncDto: " + mergedRoleFuncDto.getFunc() + "//" + mergedRoleFuncDto.getRole() + "//" + mergedRoleFuncDto.getSla());
            }
        }

        for (MergedRoleFuncDto mergedRoleFuncDto : mergedDataMap.values()) {
            mergedRoleFuncList.add(mergedRoleFuncDto);
        }

        return mergedRoleFuncList;
    }

    public void checkMergeFuncRole(RoleFuncDto roleFuncDto) {
        String roleCode = roleFuncDto.getRoleCode();
        String funcCode = roleFuncDto.getFuncCode();

//             Check if roleId and funcId exist in SYS_ROLE_FUNC
        SysRoleFuncDo sysRoleFuncDo = sysRoleFuncRepository.findByRoleCodeAndFuncCode(roleCode, funcCode);
        boolean canViewSysRole = sysRoleFuncDo != null && sysRoleFuncDo.getCanView().equals("Y");
        if (sysRoleFuncDo == null) {
            log.error("It doesn't exist in 1.0 !!!");
        }

//             Check if roleId and funcId exist in SYS_REVAMP_ROLE_FUNC
        SysRevampRoleFuncDo sysRevampRoleFuncDo = sysRevampRoleFuncRepository.findByRoleCodeAndFuncCode(roleCode, funcCode);
        boolean activeSysRevampRole = sysRevampRoleFuncDo != null && sysRevampRoleFuncDo.getActive() == 1;
        if (sysRevampRoleFuncDo == null) {
            log.error("It doesn't exist in 2.0 !!!");
        }

        System.out.println("Role Code: " + roleCode);
        System.out.println("Func Code: " + funcCode);
        System.out.println("SYS_ROLE_FUNC - canView: " + (canViewSysRole ? "Y" : "N"));
        System.out.println("SYS_REVAMP_ROLE_FUNC - active: " + (activeSysRevampRole ? "1" : "0"));
        System.out.println();
    }
    }
