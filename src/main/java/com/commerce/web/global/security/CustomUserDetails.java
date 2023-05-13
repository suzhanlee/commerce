//package com.commerce.web.global.security;
//
//import com.commerce.db.entity.member.Member;
//import java.util.Collection;
//import java.util.Collections;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class CustomUserDetails implements UserDetails {
//
//    private Member member;
//
//    public CustomUserDetails(Member member) {
//        this.member = member;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public String getPassword() {
//        return member.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return member.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
