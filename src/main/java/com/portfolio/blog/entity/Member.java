package com.portfolio.blog.entity;

import com.portfolio.blog.constant.Role;
import com.portfolio.blog.dto.MemberDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member{

    @Id
    @Column(name="member_id")
    private String id;

    private String name;

    @Column(unique = true)
    private String nickName;

    private String password;

    private Role role;

    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        // dto -> entity : 1:1 맵핑
        member.setName(memberDTO.getName());
        member.setId(memberDTO.getId());
        member.setNickName(memberDTO.getNickName());
        member.setRole(Role.USER);

       // 비밀번호를 암호화 처리
        String password = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(password);

        return member;
    }


}

