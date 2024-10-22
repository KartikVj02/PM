package com.coachbar.pms;

import com.coachbar.pms.controller.BookController;
import com.coachbar.pms.dto.BookDto;
import com.coachbar.pms.form.BookForm;
import com.coachbar.pms.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

@Test
    void testGetAllBooks(){
    List<BookDto> books = new ArrayList<>();
    BookDto dto = new BookDto();
    dto.setName("asdfgh");
    books.add(dto);
    Mockito.when(bookService.getAllBooks()).thenReturn(books);
    ResponseEntity<List<BookDto>> responseEntity = new ResponseEntity<>(HttpStatus.OK);

    responseEntity = bookController.getAllBooks();
    Assertions.assertNotNull(responseEntity);
    }

    @Test
    void testAddBooks() {

        BindingResult bindingResult = mock(BindingResult.class);

        BookForm dto = new BookForm();
        dto.setName("sd");
        dto.setPrice(200);
        dto.setQuantity(22);

        ResponseEntity<BookDto> responseEntity = bookController.addBooks(dto, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void TestUpdateBooke() {

        BookDto bookDto = new BookDto();
        bookDto.setName("jhgfdt");
        bookDto.setPrice(200);

        ResponseEntity<Void> responseEntity = bookController.updateBook(bookDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteBookTest() {
        BookDto bookDto = new BookDto();
        ResponseEntity<Void> responseEntity = bookController.deleteBook(bookDto);
        assertEquals(ResponseEntity.ok().build(), responseEntity);
    }


}
