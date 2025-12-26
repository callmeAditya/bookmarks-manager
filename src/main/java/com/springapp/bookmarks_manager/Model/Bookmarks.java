package com.springapp.bookmarks_manager.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Document(collection = "testSchema")
public class Bookmarks {

    @Id
    String id;
    String BookmarksID;
    String Title;
    String URL;
    String Description;
    Date CreatedAt;
    List<String> Categories;

}
