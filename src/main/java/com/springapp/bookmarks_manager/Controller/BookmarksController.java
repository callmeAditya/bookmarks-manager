package com.springapp.bookmarks_manager.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.bookmarks_manager.Exception.BookmarkAlreadyExistException;
import com.springapp.bookmarks_manager.Exception.ResourceNotFoundException;
import  com.springapp.bookmarks_manager.Model.Bookmarks;
import com.springapp.bookmarks_manager.Model.BookmarksDTO;
import com.springapp.bookmarks_manager.Service.BookmarksService;

@RestController
@RequestMapping("bookmarks")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BookmarksController {

    @Autowired
    private BookmarksService bookmarksService;

 

    @GetMapping("/getAll")
    public ResponseEntity<List<Bookmarks>> getAllBookmarks(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getPrincipal().toString();
        return ResponseEntity.ok(bookmarksService.getAllBookmarks(email));
    }

    @GetMapping("/test")
    public String tester(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getPrincipal().toString();
    return username;
    }

    @GetMapping("/get/{id}")
    public Bookmarks getBookmarkById(@PathVariable String id){
       return bookmarksService.getBookmarksById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBookmark(@RequestBody Bookmarks bookmark ) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();
      // 1. Logic to check for existing bookmark (example by URL)
        if (bookmarksService.existsByUrlAndEmail(bookmark.getUrl(), email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Bookmark already exist");
            // throw new BookmarkAlreadyExistException("Bookmark already exists", new Throwable());
        }

        // 2. Proceed with creation
        bookmark.setId(UUID.randomUUID().toString());
        bookmark.setCreatedAt(new Date());
        bookmark.setEmail(email);
        
        bookmarksService.createBookmark(bookmark);
        return ResponseEntity.ok("Bookmark successfully created");
    }

    @PostMapping("/update")
    public Bookmarks updateBookmarks(@RequestBody Bookmarks bookmarks){
       return bookmarksService.updateBookmarks(bookmarks);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookMark(@RequestBody BookmarksDTO bookmarksDTO){
        bookmarksService.deleteBookMark(bookmarksDTO.getId());
        return ResponseEntity.ok("Bookmark deleted");
    }


}
