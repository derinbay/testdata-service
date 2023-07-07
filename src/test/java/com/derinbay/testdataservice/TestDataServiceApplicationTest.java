package com.derinbay.testdataservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
public class TestDataServiceApplicationTest {

    @Test
    void contextLoads() {
        TestDataServiceApplication.main(new String[]{});
    }
}
