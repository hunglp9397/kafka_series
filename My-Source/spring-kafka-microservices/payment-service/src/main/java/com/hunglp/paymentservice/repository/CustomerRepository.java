package com.hunglp.paymentservice.repository;

import com.hunglp.paymentservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
