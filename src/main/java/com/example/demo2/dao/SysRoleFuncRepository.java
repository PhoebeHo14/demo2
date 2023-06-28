package com.example.demo2.dao;

import com.example.demo2.model.SysRoleFuncDo;
import com.example.demo2.pojo.RoleFuncDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRoleFuncRepository extends JpaRepository<SysRoleFuncDo, Integer> {
//    @Query("SELECT new com.example.demo2.pojo.RoleFuncDto(rf.funcNameEn, rf.funcCode, sr.roleName, sr.roleCode, rf.slaService, srf2.canView, srf.active) " +
//            "FROM SysRevampRoleFuncDo srf " +
//            "JOIN SysRoleFuncDo srf2 ON srf.roleId = srf2.roleId AND srf.funcId = srf2.funcId " +
//            "JOIN SysRoleDo sr ON srf.roleId = sr.id " +
//            "JOIN SysRevampFuncDo rf ON srf.funcId = rf.id " +
//            "WHERE srf.active = 1 AND srf2.canView = 'Y'")
//    List<RoleFuncDto> findRoleFuncWithCriteria();

    @Query(nativeQuery = true, value = "SELECT rf.FUNC_NAME_EN AS funcNameEn, rf.FUNC_CODE AS funcCode, sr.ROLE_NAME AS roleName, sr.ROLE_CODE AS roleCode, rf.SLA_SERVICE AS slaService, srf2.CAN_VIEW AS canView, srf.ACTIVE AS active " +
            "FROM SYS_REVAMP_ROLE_FUNC srf " +
            "JOIN SYS_ROLE_FUNC srf2 ON srf.ROLE_ID = srf2.ROLE_ID AND srf.FUNC_ID = srf2.FUNC_ID " +
            "JOIN SYS_ROLE sr ON srf.ROLE_ID = sr.ID " +
            "JOIN SYS_REVAMP_FUNC rf ON srf.FUNC_ID = rf.ID " +
            "WHERE srf.ACTIVE = 1 AND srf2.CAN_VIEW = 'Y'")
    List<RoleFuncDto> findRoleFuncWithCriteria();

    @Query(nativeQuery = true, value = "SELECT srf.* " +
            "FROM SYS_ROLE_FUNC srf " +
            "JOIN SYS_ROLE sr ON srf.ROLE_ID = sr.ID " +
            "JOIN SYS_REVAMP_FUNC rf ON srf.FUNC_ID = rf.ID " +
            "WHERE sr.ROLE_CODE = :roleCode AND rf.FUNC_CODE = :funcCode")
    SysRoleFuncDo findByRoleCodeAndFuncCode(@Param("roleCode") String roleCode, @Param("funcCode") String funcCode);

    @Query(nativeQuery = true, value = "SELECT * FROM SYS_ROLE_FUNC WHERE FUNC_ID = :funcId AND ROLE_ID = :roleId AND CAN_VIEW = :canView")
    List<SysRoleFuncDo> findByFuncIdAndRoleIdAndCanView(Integer funcId, Integer roleId, String canView);
}
