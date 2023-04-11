package com.commerce.web.domain.book.repository;

import com.commerce.db.entity.item.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
