package com.example.demo2.dao;

import com.example.demo2.model.SysRevampRoleFuncDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRevampRoleFuncRepository extends JpaRepository<SysRevampRoleFuncDo, Integer> {

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM SYS_REVAMP_ROLE_FUNC srf " +
            "JOIN SYS_ROLE sr ON srf.ROLE_ID = sr.ID " +
            "JOIN SYS_REVAMP_FUNC rf ON srf.FUNC_ID = rf.ID " +
            "WHERE sr.ROLE_CODE = :roleCode AND rf.FUNC_CODE = :funcCode")
    SysRevampRoleFuncDo findByRoleCodeAndFuncCode(@Param("roleCode") String roleCode, @Param("funcCode") String funcCode);

}
