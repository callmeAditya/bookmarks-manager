package com.springapp.bookmarks_manager.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.springapp.bookmarks_manager.Model.UserDTO;

public interface UserRepo extends MongoRepository<UserDTO, String>{

    UserDTO findByEmail(String email);

}

