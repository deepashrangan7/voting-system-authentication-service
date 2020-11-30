package com.cts.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByEmail(String email);
	
	public Users findByEmailAndPassword(String email,String password);

	public Users findByEmailAndPasswordAndActive(String email,String password, Integer active);

}
