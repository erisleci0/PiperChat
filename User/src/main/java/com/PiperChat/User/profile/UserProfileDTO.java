package com.PiperChat.User.profile;

public class UserProfileDTO {
    private String username;
    private Long followers;
    private Long following;

    public UserProfileDTO(String username, Long followers, Long following) {
        this.username = username;
        this.followers = followers;
        this.following = following;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }

}
