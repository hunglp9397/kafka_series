package com.hunglp.paymentservice.service;

import com.hunglp.paymentservice.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderManageService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderManageService.class);


    public void reserve(Order o) {
        LOG.info("Reserving order : ");
    }

    public void confirm(Order o) {
        LOG.info("Confirming order : ");
    }
}
