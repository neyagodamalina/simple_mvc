package org.example.app.services;


import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {
    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repository = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repository);
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repository.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for (Book book : retreiveAll()){
            if (book.getId().equals(bookIdToRemove)){
                logger.info("remove book completed " + book);
                return repository.remove(book);
            }
        }
        return false;
    }

    @Override
    public void removeItemByRegex(String queryRegex) {
        Pattern pattern = Pattern.compile(queryRegex);
        for (Book book : retreiveAll()){
            Matcher matcherAuthor = pattern.matcher(book.getAuthor());
            Matcher matcherTitle = pattern.matcher(book.getTitle());
            Matcher matcherSize = pattern.matcher(book.getSize().toString());
            if (matcherAuthor.find() || matcherTitle.find() || matcherSize.find()) {
                this.removeItemById(book.getId());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
