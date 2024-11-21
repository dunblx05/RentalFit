package com.ssafy.sports.model.dto;

import java.util.List;

public class User {
    private String userId;
    private String userPwd;
    private String userNickName;
    private Integer userStamps;

    public User(String userId, String userPwd, String userNickName, Integer userStamps) {
        super();
        this.userId = userId;
        this.userPwd = userPwd;
        this.userNickName = userNickName;
        this.userStamps = userStamps;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getUserStamps() {
        return userStamps;
    }

    public void setUserStamps(Integer userStamps) {
        this.userStamps = userStamps;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userPwd=" + userPwd + ", userNickName=" + userNickName + ", userStamps="
                + userStamps + "]";
    }
}
