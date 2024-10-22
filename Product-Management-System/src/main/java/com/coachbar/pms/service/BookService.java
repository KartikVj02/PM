package com.coachbar.pms.service;

import com.coachbar.pms.dao.BookRepository;
import com.coachbar.pms.dto.BookDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    private final BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }


    @Transactional
    public long saveBook(BookDto bookDto) {

        return bookRepository.save(bookDto);
    }

    @Transactional
    public List<BookDto> getAllBooks() {
        return bookRepository.getBooks();
    }

    @Transactional
    public void updateBook(BookDto bookDto) {

        bookRepository.update(bookDto);
    }
    @Transactional
    public void deleteBook(BookDto bookDto) {

        bookRepository.delete(bookDto);
    }
}