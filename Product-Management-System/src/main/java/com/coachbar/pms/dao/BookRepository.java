package com.coachbar.pms.dao;

import com.coachbar.pms.dto.BookDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @PersistenceContext
    EntityManager entityManager;


    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public long save(BookDto bookDto){
        entityManager.persist(bookDto);
        return  bookDto.getId();
    }

    public List<BookDto> getBooks(){
        TypedQuery<BookDto> query = entityManager.createQuery("SELECT b FROM BookDto b", BookDto.class);
        List<BookDto> list = query.getResultList();
        return list;
    }

    public void update(BookDto bookDto) {
        if (!entityManager.contains(bookDto)) {
            bookDto = entityManager.merge(bookDto);
        }
        entityManager.remove(bookDto);
    }
    public void delete(BookDto dto) {

        entityManager.remove(dto);
    }




}
