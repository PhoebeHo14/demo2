package com.example.demo2.dao;

import com.example.demo2.model.SysRevampFuncDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRevampFuncRepository extends JpaRepository<SysRevampFuncDo, Integer> {
    SysRevampFuncDo findByFuncCode(String funcCode);
    List<SysRevampFuncDo> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM SYS_REVAMP_FUNC f WHERE f.ID = :id")
    Optional<SysRevampFuncDo> findById(@Param("id") Integer id);

}
