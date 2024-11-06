package xyz.cafebabe.ucbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.cafebabe.ucbackend.mapper")
public class UcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcBackendApplication.class, args);
    }

}
