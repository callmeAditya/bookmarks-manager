package com.springapp.bookmarks_manager.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Document(collection = "users")
public class UserDTO {

    @Id
    String id;
    String email;
    String password;

}
