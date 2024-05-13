package com.example.onlimitedauction.web.member.entity;

import com.example.onlimitedauction.global.entity.BaseEntity;
import com.example.onlimitedauction.global.type.UserType;
import com.example.onlimitedauction.web.item.entity.Item;
import com.example.onlimitedauction.web.member.dto.RequestUpdateMemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserType userType;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();

    public void update(RequestUpdateMemberDto member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.password = member.getPassword();
    }
}
