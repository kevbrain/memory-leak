package com.its4u;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;


@ManagedBean(name = "allocMemoryBean")
@SessionScoped
public class AllocMemoryBean {
	
	private int counter;
	
	private int mapSize;
	
	private long counterFreeSize;
	
	private long maxmemory;
	
	private long totalmemory;
	
	private LineChartModel lineModel;
	
	private ChartData data ;
	
	private LineChartDataSet dataSet ; 
	
	private List<Object> values ;
	
	private List<String> labels ;
	
	private boolean allocmemory ;
	
	private Map<Key, String> map = new HashMap<Key, String>(1000); 
	
	
	@PostConstruct
    public void init() {
		
		System.out.println("Init Alloc Memory bean");
		this.mapSize=0;
		this.counterFreeSize=getFreeMemory();
		this.maxmemory=getMaxMemory();
		this.totalmemory=getTotalMemory();		
		this.values = new ArrayList<>();
		this.labels = new ArrayList<>();
		this.lineModel = new LineChartModel();
		this.data = new ChartData();
		this.dataSet = new LineChartDataSet();
		this.dataSet.setData(this.values);
		this.dataSet.setFill(true);
		this.dataSet.setLabel("Memory Total consumption");
		this.data.addChartDataSet(dataSet);
		this.data.setLabels(this.labels);
		this.lineModel.setData(data);
		this.allocmemory=false;
		initOneGb();
		
	}
	

	public void startAllocMemory() {
		System.out.println("Start Alloc Memory");
		
		this.allocmemory=true;		  	
	}
	
	public void stopAllocMemory() {
		System.out.println("STOP Alloc Memory");
		this.allocmemory=false;
		
	}
	
	public void cleanAllocMemory() {
		System.out.println("Clean Alloc Memory");
		this.allocmemory=false;
		this.map=null;
		this.map = new HashMap<Key, String>(1000);
		
	}
	
	public void initOneGb() {
		
		 for (int i=0;i<40000000;i++) {
		      this.map.put(new Key("dummyKey"), "value");
		      counter++;		       
		 }
		 System.out.println("1GB Allocated Memory");
	}
	
	public void check() {
		
	    if (isAllocmemory()) {
		    
	    	System.out.println("Allocate 1000000 item on map");
		    for (int i=0;i<1000000;i++) {
			      this.map.put(new Key("dummyKey"), "value");
			      counter++;		       
			    }
		} else {
			System.out.println("Allocation paused");
		}
	     
        this.mapSize=map.size();
        this.counterFreeSize=getFreeMemory();
		this.maxmemory=getMaxMemory();
		this.totalmemory=getTotalMemory();	     
    	this.values.add(getTotalMemory());
    	this.labels.add(String.valueOf(counter/1000));
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

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}


	public boolean isAllocmemory() {
		return allocmemory;
	}


	public void setAllocmemory(boolean allocmemory) {
		this.allocmemory = allocmemory;
	}
	
	
	

	  
}
