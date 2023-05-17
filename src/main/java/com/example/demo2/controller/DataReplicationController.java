package com.example.demo2.controller;

import com.example.demo2.controller.pojo.ResponseDto;
import com.example.demo2.controller.service.DataReplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Work Time Data Replication")
@RequiredArgsConstructor
public class DataReplicationController {

    private final DataReplicationService dataReplicationService;

    @PostMapping("/Work-time-data-replication")
    @Operation(summary = "複製工時統計資料", description = "將db1 table work_time的資料複製到db2 table work_time")
    public ResponseDto<String> replicateWorkTime() {
        return dataReplicationService.start();
    }
}
