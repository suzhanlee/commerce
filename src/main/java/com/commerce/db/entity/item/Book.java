package com.commerce.db.entity.item;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Book extends Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    // 저자, 책번호
    private String author;
    private String isbn;

    private static Book createBook(String author, String isbn) {
        Book book = new Book();
        book.author = author;
        book.isbn = isbn;

        return book;
    }

    // private 이어서 필드를 바꿀 수 없다...

    public Book(String author, String isbn) { // => item의 필드를 다 채울 수 없다.
        this.author = author;
        this.isbn = isbn;
    }
}
