package com.masaiqi.exchage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.masaiqi.exchage.dao")
public class DatasourceExchageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceExchageApplication.class, args);
    }

}
