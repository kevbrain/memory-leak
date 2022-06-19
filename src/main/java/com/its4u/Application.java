package com.its4u;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.its4u.AllocMemoryBean.Key;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Map<Key, String> map = new HashMap<Key, String>(1000);   
	    int counter = 0;
		for (int i=0;i<10000000;i++) {
		      map.put(new Key("dummyKey"), "value");
		      counter++;		       
		 }
		 System.out.println("1GB Allocated Memory");
		 SpringApplication.run(Application.class, args);
		 
	}
	
}