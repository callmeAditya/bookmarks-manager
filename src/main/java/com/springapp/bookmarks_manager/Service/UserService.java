package com.springapp.bookmarks_manager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springapp.bookmarks_manager.Model.UserDTO;
import com.springapp.bookmarks_manager.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	public UserDTO saveUser(UserDTO user) {
	user.setPassword(encoder.encode(user.getPassword()));
	return userRepo.save(user) ;
		
	}



}
