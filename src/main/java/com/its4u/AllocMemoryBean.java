package com.its4u;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "allocMemoryBean")
@SessionScoped
public class AllocMemoryBean {
	
	private int mapSize;
	
	private long counterFreeSize;
	
	private long maxmemory;
	
	private long totalmemory;
	
	@PostConstruct
    public void init() {
		
		System.out.println("Init Alloc Memory bean");
		this.mapSize=0;
		this.counterFreeSize=getFreeMemory();
		this.maxmemory=getMaxMemory();
		this.totalmemory=getTotalMemory();
	}

	public void startAllocMemory() {
		System.out.println("Start Alloc Memory");
		Map<Key, String> map = new HashMap<Key, String>(1000);   
	    int counter = 0;
	    while (true) {
	       // creates duplicate objects due to bad Key class
	      map.put(new Key("dummyKey"), "value");
	      counter++;
	      if (counter % 1000 == 0) {
	        System.out.println("Map size: " + map.size());
	        this.mapSize=map.size();
	        this.counterFreeSize=getFreeMemory();
			this.maxmemory=getMaxMemory();
			this.totalmemory=getTotalMemory();
	        System.out.println("Free memory after count " + counter
	         + " is " + getFreeMemory() + "MB");       
	        sleep(5);
	      }     
	    }
	}
	
	public void check() {
		this.counterFreeSize=getFreeMemory();
	}
	
	static class Key {
	    private String key; 
	    public Key(String key) {
	      this.key = key;
	    } 
	  }
	 
	  public static void sleep(long sleepFor) {
	    try {
	      Thread.sleep(sleepFor);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	 
	  public static long getFreeMemory() {
	    return Runtime.getRuntime().freeMemory() / (1024 * 1024);
	  }
	  
	  public static long getTotalMemory() {
		 return Runtime.getRuntime().totalMemory() / (1024 * 1024);
	  }
	  
	  public static long getMaxMemory() {
		  return Runtime.getRuntime().maxMemory() /  (1024 * 1024);
	  }

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	public long getCounterFreeSize() {
		return counterFreeSize;
	}

	public void setCounterFreeSize(long counterFreeSize) {
		this.counterFreeSize = counterFreeSize;
	}

	public long getMaxmemory() {
		return maxmemory;
	}

	public void setMaxmemory(long maxmemory) {
		this.maxmemory = maxmemory;
	}

	public long getTotalmemory() {
		return totalmemory;
	}

	public void setTotalmemory(long totalmemory) {
		this.totalmemory = totalmemory;
	}
	
	

	  
}
