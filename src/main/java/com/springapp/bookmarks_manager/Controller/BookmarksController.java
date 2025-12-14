package com.springapp.bookmarks_manager.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.springapp.bookmarks_manager.Model.Bookmarks;
import com.springapp.bookmarks_manager.Service.BookmarksService;

@RestController
@RequestMapping("bookmarks")
public class BookmarksController {

    @Autowired
    private BookmarksService bookmarksService;

    @GetMapping("/getAll")
    public List<Bookmarks> getAllBookmarks(){
        return bookmarksService.getAllBookmarks();
    }

    @GetMapping("/get:{id}")
    public Optional<Bookmarks> getBookmarkById(@PathVariable String id){
        return bookmarksService.getBookmarksById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBookmark(@RequestBody Bookmarks bookmark ){
        bookmark.setBookmarksID(UUID.randomUUID().toString());
        bookmarksService.createBookmark(bookmark);
        return ResponseEntity.ok("Bookmark added");
    }

    @PostMapping("/update")
    public Bookmarks updateBookmarks(@RequestBody Bookmarks bookmarks){
       return bookmarksService.updateBookmarks(bookmarks);
    }

    @DeleteMapping("/delete:{id}")
    public ResponseEntity<String> deleteBookMark(@PathVariable String id){
        bookmarksService.deleteBookMark(id);

        return ResponseEntity.ok("Bookmark deleted");
    }


}
