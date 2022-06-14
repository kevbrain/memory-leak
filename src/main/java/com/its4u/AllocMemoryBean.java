package com.its4u;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "allocMemoryBean")
@SessionScoped
public class AllocMemoryBean {
	
	@PostConstruct
    public void init() {
		
		System.out.println("Init Alloc Memory bean");
	}

	public void startAllocMemory() {
		System.out.println("Start Alloc Memory");
	}
	
	public void stopAllocMemory() {
		System.out.println("Stop Alloc Memory");
	}
}
