package com.enterprise.java.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.java.models.MyUserDetails;

public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, String> {
	
}