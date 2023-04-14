//package com.commerce.web.domain.item.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import com.commerce.db.entity.Category;
//import com.commerce.db.entity.member.Member;
//import com.commerce.db.entity.item.Item;
//import com.commerce.web.domain.category.repository.CategoryRepository;
//import com.commerce.web.domain.item.model.rq.CreateItemRq;
//import com.commerce.web.domain.item.model.rs.CreateItemRs;
//import com.commerce.web.domain.item.repository.ItemRepository;
//import com.commerce.web.domain.member.repository.MemberRepository;
//import com.commerce.web.global.exception.CannotFindCategoryException;
//import com.commerce.web.global.exception.CannotFindMemberException;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class ItemServiceTest {
//
//    @InjectMocks
//    ItemService itemService;
//
//    @Mock
//    ItemRepository itemRepository;
//
//    @Mock
//    MemberRepository memberRepository;
//
//    @Mock
//    CategoryRepository categoryRepository;
//
//
//    public CreateItemRs createItem(CreateItemRq createItemRq) {
//
//        Member findMember = memberRepository.findById(createItemRq.getMemberId()).orElseThrow(
//            CannotFindMemberException::new);
//
//        Item item = Item.toEntity(createItemRq);
//
//        item.addMember(findMember);
//
//        Category findCategory = categoryRepository.findById(createItemRq.getCategoryId())
//            .orElseThrow(CannotFindCategoryException::new);
//
//        item.addCategory(findCategory);
//        itemRepository.save(item);
//
//        return Item.toCreateItemRs(item, findMember.getId());
//    }
//
//    @Test
//    void createItemTest() {
//
//        //given
//        CreateItemRq createItemRq = createLapTop();
//
//        //stub 1
//        Member findMember = Member.createNormal("suchan","wlscww@kakao.com","010-1234-1234");
//        when(memberRepository.findById(findMember.getId())).thenReturn(Optional.of(findMember));
//
//        System.out.println("aa" + findMember.getId());
//        System.out.println(findMember);
//
//        //stub 2
//        Category findCategory = Category.createCategory("전자기기");
//        when(categoryRepository.findById(findCategory.getId())).thenReturn(Optional.of(findCategory));
//
//        System.out.println("bb" + findCategory.getId());
//        System.out.println(findCategory);
//
//        //when
//        CreateItemRs createItemRs = itemService.createItem(createItemRq);
//
//        //then
//        assertThat(createItemRs.getItemName()).isEqualTo("갤럭시북");
//        assertThat(createItemRs.getCpu()).isEqualTo("i5");
//
//
//    }
//
//    private static CreateItemRq createLapTop() {
//        return CreateItemRq
//            .builder()
//            .memberId(1L)
//            .categoryId(1L)
//            .itemName("갤럭시북")
//            .price(1000000)
//            .description("노트북입니다.")
//            .image("1234")
//            .ram(8)
//            .cpu("i5")
//            .ssd(512)
//            .build();
//    }
//
//}