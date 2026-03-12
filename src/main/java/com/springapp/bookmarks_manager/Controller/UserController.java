package com.springapp.bookmarks_manager.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.bookmarks_manager.Exception.UserAlreadyExistsException;
import com.springapp.bookmarks_manager.Exception.UserDoesNotExistException;
import com.springapp.bookmarks_manager.Model.UserDTO;
import com.springapp.bookmarks_manager.Repository.UserRepo;
import com.springapp.bookmarks_manager.Service.JwtService;
import com.springapp.bookmarks_manager.Service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

     @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    // @PostMapping("/register")
	// public UserDTO register(@RequestBody UserDTO user) {
    //     user.setId(UUID.randomUUID().toString());
	// //   return ResponseEntity.ok("User registered successfully ✅") ;
    // return userService.saveUser(user);

	// }  
    
    @PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDTO user) throws Exception {
        user.setId(UUID.randomUUID().toString());
        try{
            userService.saveUser(user);

        }catch(UserAlreadyExistsException e1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: User already exist");
        }
	  return ResponseEntity.ok("User registered successfully ✅") ;
	}

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        if(userRepo.findByEmail(userDTO.getEmail())==null){
			throw new UserDoesNotExistException("User does not exists");
		}
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));

        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(userDTO.getEmail());
        }

        return "error";
        // return null;
    }

    


}
