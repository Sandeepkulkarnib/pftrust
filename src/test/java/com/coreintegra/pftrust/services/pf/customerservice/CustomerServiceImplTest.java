package com.coreintegra.pftrust.services.pf.customerservice;

import com.coreintegra.pftrust.projections.PfUserProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void getPfUsers() {

        Page<PfUserProjection> pfUsers = customerService.getPfUsers("9000000", 10, 1);

    }

}