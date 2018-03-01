package com.zj.cloud.microserviceprovideruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.zj.cloud.microserviceprovideruser.entity.User;
import com.zj.cloud.microserviceprovideruser.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable Long id){
		return userRepository.findOne(id);
	}

	@GetMapping("/serviceUrl")
	public String serviceUrl(){
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("microservice-provider-user", false);
		return instanceInfo.getHomePageUrl();
	}
	
	
	
}
