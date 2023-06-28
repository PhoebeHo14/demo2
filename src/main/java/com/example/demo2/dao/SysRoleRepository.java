package com.example.demo2.dao;

import com.example.demo2.model.SysRoleDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRoleDo, Integer> {
    SysRoleDo findByRoleCode(String roleCode);

    @Query(nativeQuery = true, value = "SELECT * FROM SYS_ROLE WHERE ID = :id")
    Optional<SysRoleDo> findById(@Param("id") Integer id);


}
