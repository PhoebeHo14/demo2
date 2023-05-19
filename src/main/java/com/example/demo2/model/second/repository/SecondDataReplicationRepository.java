package com.example.demo2.model.second.repository;

import com.example.demo2.model.second.entity.SecondWorkTimeDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SecondDataReplicationRepository extends JpaRepository<SecondWorkTimeDo, Long> {
    SecondWorkTimeDo findByAccountIdAndCheckInDate(Integer accountId, LocalDate checkInDate);
}
