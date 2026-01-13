package com.springapp.bookmarks_manager.Model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class User {

    String id;
    String username;
    String password;
    Bookmarks bookmarks;

}
