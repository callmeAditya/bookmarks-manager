package com.springapp.bookmarks_manager.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.springapp.bookmarks_manager.Model.Bookmarks;

public interface BookmarksRepo extends MongoRepository<Bookmarks, String>{

    List<Bookmarks> findByEmail(String email);

}
