package com.commerce.web.domain.member.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.auth.model.rq.SignUpRq;
import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.CannotFindMemberException;
import com.commerce.web.global.security.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenFactory jwtTokenFactory;

    public void createMember(CreateMemberRq rq) {
        Member member = Member.createSeller(rq.getName(), rq.getEmail(), rq.getPhone());
        memberRepository.save(member);
    }

    public void deleteMember(DeleteMemberRq rq) {
        memberRepository.deleteById(rq.getMemberId());
    }

    public void deleteItem(DeleteMemberRq rq) {
        Member member = memberRepository.findById(rq.getMemberId())
            .orElseThrow(CannotFindMemberException::new);
        member.getItemList().remove(0);

        for (Item item : member.getItemList()) {
            System.out.println("item = " + item);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByName(username)
            .orElseThrow(() -> new RuntimeException(" 일치하는 회원이름이 없습니다."));
    }

    public JwtTokenDto authenticate(JwtTokenDto jwtTokenDto) {
        String token = jwtTokenDto.getToken();

        String username = jwtTokenFactory.getUsername(token);
        String email = jwtTokenFactory.getEmail(token);

        Member findMember = memberRepository.findByName(username)
            .orElseThrow(() -> new RuntimeException(" 일치하는 회원이름이 없습니다."));

        if (passwordEncoder.matches(email, findMember.getEmail())) {
            throw new RuntimeException("이메일이 일치하지 않습니다");
        }

        return jwtTokenDto;
    }

    public void registerMember(SignUpRq rq) {

        String email = passwordEncoder.encode(rq.getEmail());

        Member member = Member.createMemberByUsernameAndEmail(rq.getUsername(),
            email);

        memberRepository.save(member);

    }
}

