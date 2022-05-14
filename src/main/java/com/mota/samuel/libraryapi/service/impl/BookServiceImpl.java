package com.mota.samuel.libraryapi.service.impl;

import com.mota.samuel.libraryapi.api.model.entity.Book;
import com.mota.samuel.libraryapi.exception.BusinessException;
import com.mota.samuel.libraryapi.model.repository.BookRepository;
import com.mota.samuel.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if(repository.existsByIsbn(book.getIsbn())){
            throw new BusinessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }
}
