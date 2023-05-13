//package com.commerce.web.global.security;
//
//import com.commerce.web.domain.member.service.FindMemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CustomUserService implements UserDetailsService {
//
//    private final FindMemberService findMemberService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new CustomUserDetails(findMemberService.findByUsernameOrElseThrow(username));
//    }
//}
