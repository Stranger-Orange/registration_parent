package comm.orange.registration.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Orange
 * @create 2021-06-10 10:46
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.orange"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.orange"})
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}

