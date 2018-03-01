package com.zj.cloud.microserviceprovideruser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zj.cloud.microserviceprovideruser.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
