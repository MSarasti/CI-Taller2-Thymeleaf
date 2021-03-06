package com.taller2;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.taller2.model.person.*;
import com.taller2.model.prod.*;
import com.taller2.service.implementation.*;

@SpringBootApplication
@EnableJpaRepositories("com.taller2.repository")
@EntityScan(basePackages = {"com.taller2.security","com.taller2.model"})
@ComponentScan(basePackages = {"com.taller2.security","com.taller2.repository","com.taller2.service.implementation","com.taller2.controller"})
public class Taller2MsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Taller2MsApplication.class, args);
		UserServiceImpl userService = c.getBean(UserServiceImpl.class);
		ProductServiceImpl pService = c.getBean(ProductServiceImpl.class);
		UserApp user1 = new UserApp();
		user1.setUsername("Simba1");
		user1.setPassword("{noop}Simba1234");
		user1.setType(UserType.ADMIN);
		
		UserApp user2 = new UserApp();
		user2.setUsername("Ginger1");
		user2.setPassword("{noop}Ginger1234");
		user2.setType(UserType.OPERATOR);
		
		userService.save(user1);
		userService.save(user2);
		
		Productcategory pc = new Productcategory();
		pc.setName("Food");
		
		Productsubcategory psc = new Productsubcategory();
		psc.setName("Meat");
		psc.setProductcategory(pc);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg");
		
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("pound");
		
		pService.saveProductCategory(pc);
		pService.saveProductSubcategory(psc);
		pService.saveUnitmeasure(um1);
		pService.saveUnitmeasure(um2);
		
	}

}
