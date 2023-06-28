package com.example.demo2.dao;

import com.example.demo2.pojo.LittleRoleFuncDto;
import com.example.demo2.pojo.RoleFuncDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface RoleFuncMapper {

//    @Select("SELECT rf.FUNC_NAME_EN, rf.FUNC_CODE, sr.ROLE_NAME, sr.ROLE_CODE, rf.SLA_SERVICE, srf2.CAN_VIEW, srf.ACTIVE " +
//            "FROM SYS_REVAMP_ROLE_FUNC srf " +
//            "JOIN SYS_ROLE_FUNC srf2 ON srf.ROLE_ID = srf2.ROLE_ID AND srf.FUNC_ID = srf2.FUNC_ID " +
//            "JOIN SYS_ROLE sr ON srf.ROLE_ID = sr.ID " +
//            "JOIN SYS_REVAMP_FUNC rf ON srf.FUNC_ID = rf.ID " +
//            "JOIN SYS_FUNC sf ON srf2.FUNC_ID = sf.ID" +
//            "WHERE srf.ACTIVE = 1 AND srf2.CAN_VIEW = 'Y'")
//    @Result(property = "funcNameEn", column = "FUNC_NAME_EN")
//    @Result(property = "funcCode", column = "FUNC_CODE")
//    @Result(property = "roleName", column = "ROLE_NAME")
//    @Result(property = "roleCode", column = "ROLE_CODE")
//    @Result(property = "slaService", column = "SLA_SERVICE")
//    @Result(property = "canView", column = "CAN_VIEW")
//    @Result(property = "active", column = "ACTIVE")
//    List<RoleFuncDto> findRoleFuncWithCriteria();

    @Select("SELECT rf.FUNC_NAME_EN, rf.FUNC_CODE, sr.ROLE_NAME, sr.ROLE_CODE, rf.SLA_SERVICE, srf2.CAN_VIEW, srf.ACTIVE " +
            "FROM SYS_REVAMP_ROLE_FUNC srf " +
            "JOIN SYS_ROLE_FUNC srf2 ON srf.ROLE_ID = srf2.ROLE_ID AND srf.FUNC_ID = srf2.FUNC_ID " +
            "JOIN SYS_ROLE sr ON srf.ROLE_ID = sr.ID " +
            "JOIN SYS_REVAMP_FUNC rf ON srf.FUNC_ID = rf.ID " +
            "JOIN SYS_FUNC f ON rf.FUNC_CODE = f.FUNC_CODE " +
            "WHERE srf.ACTIVE = 1 AND srf2.CAN_VIEW = 'Y'")
    @Result(property = "funcNameEn", column = "FUNC_NAME_EN")
    @Result(property = "funcCode", column = "FUNC_CODE")
    @Result(property = "roleName", column = "ROLE_NAME")
    @Result(property = "roleCode", column = "ROLE_CODE")
    @Result(property = "slaService", column = "SLA_SERVICE")
    @Result(property = "canView", column = "CAN_VIEW")
    @Result(property = "active", column = "ACTIVE")
    List<RoleFuncDto> findRoleFuncWithCriteria();


    @Select("SELECT srf.FUNC_NAME_EN FROM SYS_REVAMP_FUNC srf WHERE ID = 1")
    String findTest();

    @Select("SELECT rf.FUNC_NAME_EN, sr.ROLE_NAME " +
            "FROM SYS_REVAMP_FUNC rf " +
            "JOIN SYS_ROLE sr")
    @Results({
            @Result(property = "funcNameEn", column = "FUNC_NAME_EN"),
            @Result(property = "roleName", column = "ROLE_NAME")
    })
    List<LittleRoleFuncDto> getRoleFuncs(); // 请替换成实际的参数类型和名称


}
