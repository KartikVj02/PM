package com.coachbar.pms.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookDto {


    @Id
    int id;
    @Column(name = "Book_Name", length = 50)
    String name;
    @Column(name = "Description", length = 50)
    String description;
    @Column(name = "Price", length = 50)
    int price;
    @Column(name = "Quantity", length = 50)
    int quantity;


}
