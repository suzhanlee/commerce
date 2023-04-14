//package com.commerce.web.domain.book.service;
//
//import com.commerce.db.entity.item.Book;
//import com.commerce.db.entity.item.Item;
//import com.commerce.web.domain.book.model.rq.DeleteBookRq;
//import com.commerce.web.domain.book.model.rq.SaveBookRq;
//import com.commerce.web.domain.book.repository.BookRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class BookService {
//
//    private final BookRepository bookRepository;
//
//
//    public void saveBook(SaveBookRq rq, Item item) {
//        Book book = Book.create(rq.getAuthor(), rq.getIsbn(), item);
//        bookRepository.save(book);
//    }
//
//    public void deleteBook(DeleteBookRq rq) {
//        bookRepository.deleteById(rq.getBookId());
//    }
//}
