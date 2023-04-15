package com.commerce.web.domain.book.controller;

import com.commerce.web.domain.book.model.rq.DeleteBookRq;
import com.commerce.web.domain.book.model.rq.SaveBookRq;
import com.commerce.web.domain.item.service.ItemService;
import com.commerce.web.global.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "책 관련 API")
public class BookController {

    private final ItemService itemService;

    @Operation(summary = "책 생성")
    @PostMapping(ApiPath.BOOK)
    public void saveBook(@Validated @RequestBody SaveBookRq rq){
        itemService.saveBook(rq);
    }

//    @Operation(summary = "책 삭제")
//    @DeleteMapping(ApiPath.BOOK)
//    public void deleteBook(@Validated @RequestBody DeleteBookRq rq) {
//        itemService.deleteBook(rq);
//    }
}
