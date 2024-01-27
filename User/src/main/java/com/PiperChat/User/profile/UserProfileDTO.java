package com.PiperChat.User.profile;

public class UserProfileDTO {
    private String userName;
    private Long followers;
    private Long following;

    public UserProfileDTO(String userName, Long followers, Long following) {
        this.userName = userName;
        this.followers = followers;
        this.following = following;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
