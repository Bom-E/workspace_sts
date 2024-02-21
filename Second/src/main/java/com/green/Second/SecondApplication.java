package com.green.Second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecondApplication {

	public static void main(String[] args) {
		System.out.println("집에서 수정");
		SpringApplication.run(SecondApplication.class, args);
	}

}
