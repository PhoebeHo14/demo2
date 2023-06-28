package com.example.demo2.dao;

import com.example.demo2.model.SysFuncDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysFuncRepository extends JpaRepository<SysFuncDo, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM SYS_FUNC sf WHERE sf.FUNC_CODE = :funcCode")
    SysFuncDo findByFuncCode(@Param("funcCode") String funcCode);

}

