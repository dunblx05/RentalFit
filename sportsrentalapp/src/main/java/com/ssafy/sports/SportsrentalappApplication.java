package com.ssafy.sports;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import com.ssafy.sports.model.dao.UserDao;

@EnableCaching
@SpringBootApplication
public class SportsrentalappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsrentalappApplication.class, args);
    }

}
