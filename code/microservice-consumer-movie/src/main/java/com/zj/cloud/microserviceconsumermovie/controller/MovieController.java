package com.zj.cloud.microserviceconsumermovie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.zj.cloud.microserviceconsumermovie.entity.User;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/movie/{id}")
	public User movie(@PathVariable Long id){
		return restTemplate.getForObject("http://localhost:7900/getUserById/"+id, User.class);
	}

	@GetMapping("/serviceUrl")
	public String serviceUrl(){
		InstanceInfo instanceInfo1 = eurekaClient.getNextServerFromEureka("microservice-consumer-movie", false);
		String url1 = instanceInfo1.getHomePageUrl();
		InstanceInfo instanceInfo2 = eurekaClient.getNextServerFromEureka("microservice-provider-user", false);
		String url2 = instanceInfo2.getHomePageUrl();
		return url1+"  "+url2;
	}
	
	@GetMapping("/instance-info")
	public ServiceInstance showInfo(){
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		return serviceInstance;
		
	}
	
}
