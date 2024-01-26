package com.PiperChat.User;

import com.PiperChat.User.Profile.UserProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT NEW com.PiperChat.User.Profile.UserProfileDTO(u.userName, "
            + "(SELECT COUNT(f1) FROM Follower f1 WHERE f1.followee.id = u.id), "
            + "(SELECT COUNT(f2) FROM Follower f2 WHERE f2.follower.id = u.id)) "
            + "FROM User u")
    List<UserProfileDTO> findUserProfile();

    @Query("SELECT NEW com.PiperChat.User.Profile.UserProfileDTO(u.userName, "
            + "(SELECT COALESCE(COUNT(f1), 0) FROM Follower f1 WHERE f1.followee.id = u.id), "
            + "(SELECT COALESCE(COUNT(f2), 0) FROM Follower f2 WHERE f2.follower.id = u.id)) "
            + "FROM User u WHERE u.userName = :username")
    Optional<UserProfileDTO> findUserByUsername(String username);

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}
