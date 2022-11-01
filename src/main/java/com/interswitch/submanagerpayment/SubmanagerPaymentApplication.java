package com.interswitch.submanagerpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.interswitch.**"})
@EnableJpaRepositories(basePackages = {"com.interswitch.**"})
@SpringBootApplication
public class SubmanagerPaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SubmanagerPaymentApplication.class, args);
	}
}
