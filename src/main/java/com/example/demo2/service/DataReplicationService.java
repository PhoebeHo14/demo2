//package com.example.demo2.service;
//
//import com.example.demo2.model.first.entity.FirstWorkTimeDo;
//import com.example.demo2.model.first.repository.FirstDataReplicationRepository;
//import com.example.demo2.model.second.entity.SecondWorkTimeDo;
//import com.example.demo2.model.second.repository.SecondDataReplicationRepository;
//import com.example.demo2.pojo.ResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DataReplicationService {
//    private final MessageSource messageSource;
//    private final FirstDataReplicationRepository firstDataReplicationRepository;
//    private final SecondDataReplicationRepository secondDataReplicationRepository;
//
//    public ResponseDto<String> start() {
//        List<FirstWorkTimeDo> db1WorkTimeList = firstDataReplicationRepository.findAll();
//
//        for (FirstWorkTimeDo workTime : db1WorkTimeList) {
//            Integer accountId = workTime.getAccountId();
//            LocalDate checkInDate = workTime.getCheckInDate();
//
//            SecondWorkTimeDo existingWorkTimeDo = secondDataReplicationRepository.findByAccountIdAndCheckInDate(accountId, checkInDate);
//
//            if (existingWorkTimeDo != null) {
//                existingWorkTimeDo.setWorkMinute(workTime.getWorkMinute());
//                secondDataReplicationRepository.save(existingWorkTimeDo);
//            } else {
//                SecondWorkTimeDo newWorkTime = new SecondWorkTimeDo();
//                newWorkTime.setAccountId(accountId);
//                newWorkTime.setWorkMinute(workTime.getWorkMinute());
//                newWorkTime.setCheckInDate(workTime.getCheckInDate());
//                secondDataReplicationRepository.save(newWorkTime);
//            }
//        }
//
//        ResponseDto<String> responseDto = new ResponseDto<>();
//        responseDto.setStatus(1);
//        String message = messageSource.getMessage("replicate.data.success", null, LocaleContextHolder.getLocale());
//        responseDto.setMessage(message);
//
//        return responseDto;
//    }
//}
