package com.example.demo2.controller.service;

import com.example.demo2.controller.pojo.ResponseDto;
import com.example.demo2.model.first.entity.FirstWorkTimeDo;
import com.example.demo2.model.first.repository.FirstDataReplicationRepository;
import com.example.demo2.model.second.entity.SecondWorkTimeDo;
import com.example.demo2.model.second.repository.SecondDataReplicationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Check In")
@RequiredArgsConstructor
public class DataReplicationService {
    private final MessageSource messageSource;
    private final FirstDataReplicationRepository firstDataReplicationRepository;
    private final SecondDataReplicationRepository secondDataReplicationRepository;

    public ResponseDto<String> start() {
        List<FirstWorkTimeDo> db1WorkTimeList = firstDataReplicationRepository.findAll();

        for (FirstWorkTimeDo workTime : db1WorkTimeList) {

            SecondWorkTimeDo secondWorkTimeDo = new SecondWorkTimeDo();
            secondWorkTimeDo.setAccountId(workTime.getAccountId());
            secondWorkTimeDo.setWorkTime(workTime.getWorkTime());
            secondWorkTimeDo.setCheckInDate(workTime.getCheckInDate());

            secondDataReplicationRepository.save(secondWorkTimeDo);
        }

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String message = messageSource.getMessage("replicate.data.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(message);

        return responseDto;
    }
}
