package com.springapp.bookmarks_manager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springapp.bookmarks_manager.Exception.ResourceNotFoundException;
import com.springapp.bookmarks_manager.Model.Bookmarks;
import com.springapp.bookmarks_manager.Repository.BookmarksRepo;

@Service
public class BookmarksService {

    @Autowired
    private BookmarksRepo bookmarksRepo;

    public List<Bookmarks> getAllBookmarks(String email) {
        List<Bookmarks> bookmarks = bookmarksRepo.findByEmail(email);
        return bookmarks;
    }

    public Optional<Bookmarks> getBookmarksById(String id) {
        return bookmarksRepo.findById(id);
    }

    public void createBookmark(Bookmarks bookmark) {
        bookmarksRepo.save(bookmark);
    }

    public void deleteBookMark(String id) {
        bookmarksRepo.deleteById(id);
    }

    public Bookmarks updateBookmarks(Bookmarks bookmarks) {
        Optional<Bookmarks> res = bookmarksRepo.findById(bookmarks.getId());
        System.out.println(res);
        if(res.isEmpty()){
            throw new ResourceNotFoundException("Bookmark not found");
        }
        return bookmarksRepo.save(bookmarks);
    }





}
