package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final ProjectRepository<Book> projectRepository;

    @Autowired
    public BookService(ProjectRepository<Book> projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Book> getAllBooks(){
        return projectRepository.retreiveAll();
    }

    public void saveBook(Book book){
        projectRepository.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return projectRepository.removeItemById(bookIdToRemove);
    }

    public void removeBookByRegex(String queryRegex) {
        projectRepository.removeItemByRegex(queryRegex);
    }
}
