package org.example.service;

import org.example.entity.BookEntity;
import org.example.exception.CustomException;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookEntity createBookAndUpdateStock(BookEntity bookEntity) {

//        int a[]  = {1,2,3};


        BookEntity book = bookRepository.save(bookEntity); // Save book
//        bookStockRepository.updateStock(bookStock); // Update stock
        if (Integer.valueOf(bookEntity.getReleaseYear()) < 2000) {
            throw new CustomException("Invalid release year"); // Rollback the transaction
        }
        return book;
    }

}
