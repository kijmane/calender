package com.example.schedule.util;

public class WebSocketUtils {
    public static String buildUserQueue(String username) {
        return "/user/" + username + "/queue/notifications";
    }
}
