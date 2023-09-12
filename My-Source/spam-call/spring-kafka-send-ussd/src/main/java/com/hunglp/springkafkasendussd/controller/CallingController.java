package com.hunglp.springkafkasendussd.controller;


import com.hunglp.springkafkasendussd.model.CallingInfo;
import com.hunglp.springkafkasendussd.service.CallingProducer;
import com.hunglp.springkafkasendussd.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/calling-info")
public class CallingController {


    private static final Logger LOG = LoggerFactory.getLogger(CallingController.class);

    private final CallingProducer callingProducer;

    public CallingController(CallingProducer callingProducer) {
        this.callingProducer = callingProducer;
    }

    @PostMapping
    public List<CallingInfo> createCalling() {
        List<CallingInfo> callInfoList = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            CallingInfo callingInfo = new CallingInfo();
            callingInfo.setId(i);
            callingInfo.setCaller(CommonUtil.generateRandomPhone());
            callingInfo.setCalled(CommonUtil.generateRandomPhone());
            callingInfo.setStartTime(CommonUtil.generateRandomDateTime(null));
            callingInfo.setEndTime(CommonUtil.generateRandomDateTime(callingInfo.getStartTime()));
            callInfoList.add(callingInfo);
        }

        callInfoList.forEach(callInfo -> {
            try {
                callingProducer.produceCallInfo(callInfo);
                Thread.sleep(5000);
            } catch (ExecutionException e) {
                LOG.error("Error white create calling, Exception: {}", e.getMessage());
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                LOG.error("Error white create calling, Exception: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        });

        return callInfoList;
    }

}
