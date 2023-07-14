package com.qkrmekem.autocommittest;

import com.qkrmekem.autocommittest.component.UserName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AutoCommitTestApplication {



	public static void main(String[] args) {
		SpringApplication.run(AutoCommitTestApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertiesConfig.class);

		System.out.println(context.getBean("userName", UserName.class).getUsername());
	}

}
