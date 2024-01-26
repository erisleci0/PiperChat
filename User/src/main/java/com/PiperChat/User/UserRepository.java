package com.PiperChat.User;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT NEW com.PiperChat.User.UserProfileDTO(u.userName, "
            + "(SELECT COUNT(f1) FROM Follower f1 WHERE f1.followee.id = u.id), "
            + "(SELECT COUNT(f2) FROM Follower f2 WHERE f2.follower.id = u.id)) "
            + "FROM User u")
    List<UserProfileDTO> findUserProfile();

    @Query("SELECT NEW com.PiperChat.User.UserProfileDTO(u.userName, "
            + "(SELECT COALESCE(COUNT(f1), 0) FROM Follower f1 WHERE f1.followee.id = u.id), "
            + "(SELECT COALESCE(COUNT(f2), 0) FROM Follower f2 WHERE f2.follower.id = u.id)) "
            + "FROM User u WHERE u.userName = :username")
    UserProfileDTO findUserByUsername(String username);

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}