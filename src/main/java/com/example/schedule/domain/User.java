package com.example.schedule.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // JPA 엔티티로 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 ID , 자동생성

    @Column(nullable = false, unique = true)
    private String email; // 이메일 , 고유 값 null (X)

    @Column(nullable = false)
    private String password; // 비밀번호 , null (X)

    private String name; // 사용자 이름

    @Enumerated(EnumType.STRING)
    private Role role; // 사용자 역할 , USER OR ADMIN
    // 빌더 패턴 사용 위한 static 메서드
    public static UserBuilder builder() {
        return new UserBuilder();
    }
    // 각 필드 설정할 수 있는 메서드들
    public static class UserBuilder {
        private String email;
        private String password;
        private String name;
        private Role role;

        UserBuilder() {
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }
        // User 객체 생성 및 반환
        public User build() {
            User user = new User();
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setName(this.name);
            user.setRole(this.role);
            return user;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}