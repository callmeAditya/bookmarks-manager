package com.springapp.bookmarks_manager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.bookmarks_manager.Model.Bookmarks;
import com.springapp.bookmarks_manager.Repository.BookmarksRepo;

@Service
public class BookmarksService {

    @Autowired
    private BookmarksRepo bookmarksRepo;

    public List<Bookmarks> getAllBookmarks() {
        // TODO Auto-generated method stub
        return bookmarksRepo.findAll();
    }

    public Optional<Bookmarks> getBookmarksById(String id) {
        return bookmarksRepo.findById(id);
    }

    public void createBookmark(Bookmarks bookmark) {
        // TODO Auto-generated method stub
        bookmarksRepo.save(bookmark);
    }

    public void deleteBookMark(String id) {
        // TODO Auto-generated method stub
        bookmarksRepo.deleteById(id);
    }

    public Bookmarks updateBookmarks(Bookmarks bookmarks) {
        // TODO Auto-generated method stub
        return bookmarksRepo.save(bookmarks);
    }





}
