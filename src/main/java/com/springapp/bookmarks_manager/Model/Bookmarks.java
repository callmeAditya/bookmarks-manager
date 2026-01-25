package com.springapp.bookmarks_manager.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Document(collection = "bookmarks")
@CompoundIndex(def = "{'email': 1, 'url': 1}", unique = true)
public class Bookmarks {

    @Id
    String id;
    String email;
    String BookmarksID;
    String Title;
    String url;
    String Description;
    Date CreatedAt;
    List<String> Categories;

}
