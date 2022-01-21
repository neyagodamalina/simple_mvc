package org.example.app.services;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository<T> {

    List<T> retreiveAll();

    void store(T book);
}
