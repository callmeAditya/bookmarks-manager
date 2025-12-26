package com.springapp.bookmarks_manager.Model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BookmarksDTO {

    @Id
    String id;

}
