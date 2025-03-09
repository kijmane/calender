package com.example.schedule.dto;
import com.example.schedule.domain.User;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class ScheduleRequest {
    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 10, message = "일정 제목은 10자 이내로 입력해주세요.")
    private String title;
    private String content;
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}