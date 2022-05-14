package com.mota.samuel.libraryapi.model.repository;

import com.mota.samuel.libraryapi.api.model.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("Test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
    public void returnTrueWhenIsbnExists(){
        //cenario
        String isbn = "123";
        Book book = Book.builder().title("Aventuras").author("Fulano").isbn(isbn).build();
        entityManager.persist(book);

        // execucao
        boolean exists = repository.existsByIsbn(isbn);

        //verificacao
        Assertions.assertThat(exists).isTrue();

    }

    @Test
    @DisplayName("Deve retornar false quando existir um livro na base com o isbn informado")
    public void returnFalseWhenIsbnDoesntExist(){
        //cenario
        String isbn = "123";

        // execucao
        boolean exists = repository.existsByIsbn(isbn);

        //verificacao
        Assertions.assertThat(exists).isFalse();

    }
}
