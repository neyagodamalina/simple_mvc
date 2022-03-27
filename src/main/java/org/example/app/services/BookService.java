package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final ProjectRepository<Book> bookRepository;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.retreiveAll();
    }

    public void saveBook(Book book){
        bookRepository.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepository.removeItemById(bookIdToRemove);
    }
}
