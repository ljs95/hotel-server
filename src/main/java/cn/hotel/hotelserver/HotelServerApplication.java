package cn.hotel.hotelserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.hotel.hotelserver.mapper")
public class HotelServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelServerApplication.class, args);
    }

}
