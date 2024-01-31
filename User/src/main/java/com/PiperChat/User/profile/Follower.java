package com.PiperChat.User.profile;

import com.PiperChat.User.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private UserEntity follower;
    @ManyToOne
    @JoinColumn(name = "followee_id")
    private UserEntity followee;
}
