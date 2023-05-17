package com.example.demo2.model.second.repository;

import com.example.demo2.model.second.entity.SecondWorkTimeDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondDataReplicationRepository extends JpaRepository<SecondWorkTimeDo, Long> {
}
