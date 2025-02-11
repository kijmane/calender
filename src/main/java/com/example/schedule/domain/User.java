package com.example.schedule.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // JPA Entity로 데이터베이스에 매핑
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 필드 자동 생성되고 기본 키로 사용
    private Long id;

    private String name; // 사용자 이름

    @Column(nullable = false, unique = true) // 이메일 필수 , 유일한 값
    private String email;

    @Column(nullable = false) // 비밀번호 필수
    private String password;

    @Enumerated(EnumType.STRING) // 역할 (Role)은 열거형 타입으로 정의
    private Role role;

    @CreatedDate // 생성일 자동 설정
    private Date createdDate;

    @LastModifiedDate // 수정일 자동 설정
    private Date modifiedDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
    // 빌더 패턴을 위한 메서드
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String password;
        private Role role;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            User user = new User();
            user.setName(this.name);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setRole(this.role);
            return user;
        }
    }
}