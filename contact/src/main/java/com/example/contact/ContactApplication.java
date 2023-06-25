package com.example.contact;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ContactApplication {

	public static void main(String[] args) {
		
		SpringApplication app=new SpringApplication(ContactApplication.class); 
        app.setDefaultProperties(Collections
		          .singletonMap("server.port", "8088"));
				try{
					app.run(args);
				}
				catch(Exception e){
					System.out.println("-------------------------"+e);
				}
		        
	}

}
