package com.hunglp.paymentservice.service;

import com.hunglp.paymentservice.domain.Order;
import com.hunglp.paymentservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class OrderManageService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderManageService.class);

    private final CustomerRepository customerRepository;

    public OrderManageService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void reserve(Order o) {
        LOG.info("Reserving order : ");
    }

    public void confirm(Order o) {
        LOG.info("Confirming order : ");
    }
}
