package org.example.app.services;


import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {
    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repository = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repository);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repository.add(book);
;
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()){
            if (book.getId().equals(bookIdToRemove)){
                logger.info("remove book completed " + book);
                return repository.remove(book);
            }
        }
        return false;
    }
}
