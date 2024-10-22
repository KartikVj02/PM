package com.coachbar.pms.form;

import com.coachbar.pms.dto.BookDto;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {


    int id;
    @NotEmpty(message = "please enter name") String name;
    String description;
    @NotNull(message = "please enter price") Integer price;
    @NotNull(message = "please enter quantity") Integer quantity;


    public BookDto getDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookDto.setName(name);
        bookDto.setDescription(description);
        bookDto.setPrice(price);
        bookDto.setQuantity(quantity);
        return bookDto;
    }

}
