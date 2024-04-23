package br.com.consig.custody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CustodyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustodyApplication.class, args);
	}

}
