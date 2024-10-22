package com.coachbar.pms.controller;

import com.coachbar.pms.dto.BookDto;
import com.coachbar.pms.form.BookForm;
import com.coachbar.pms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity addBooks(@RequestBody BookForm form, BindingResult bindingResult) {

        String errorMessage = validate(bindingResult);
        if (errorMessage != "noError") {
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        BookDto bookDto = form.getDto();
        bookService.saveBook(bookDto);
        return new ResponseEntity<>("User created: " + bookDto.getName(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {
        bookService.updateBook(bookDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBook(@RequestBody BookDto bookDto) {
        bookService.deleteBook(bookDto);
        return ResponseEntity.ok().build();
    }

    public String validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return errorMessage;
        }
        return "noError";
    }

}