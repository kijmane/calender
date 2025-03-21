package com.example.schedule.entity;

import com.example.schedule.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

   public void changePassword(String newPassword) {
       this.password = newPassword;
   }

   public void changeName(String newName) {
       this.name = newName;
   }
}