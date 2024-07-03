package com.kishore.whatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishore.whatsapp.modal.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
