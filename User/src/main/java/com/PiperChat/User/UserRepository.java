package com.PiperChat.User;

import com.PiperChat.User.profile.UserProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT NEW com.PiperChat.User.profile.UserProfileDTO(u.username, "
            + "(SELECT COUNT(f1) FROM Follower f1 WHERE f1.followee.id = u.id), "
            + "(SELECT COUNT(f2) FROM Follower f2 WHERE f2.follower.id = u.id)) "
            + "FROM UserEntity u")
    List<UserProfileDTO> findUserProfile();

    @Query("SELECT NEW com.PiperChat.User.profile.UserProfileDTO(f.followee.username, " +
            "(SELECT COUNT(f1) FROM Follower f1 WHERE f1.followee.id = f.followee.id), " +
            "(SELECT COUNT(f2) FROM Follower f2 WHERE f2.follower.id = f.followee.id)) " +
            "FROM Follower f WHERE f.follower.username = :username")
    List<UserProfileDTO> findFollowingByUsername(@Param("username") String username);


    @Query("SELECT NEW com.PiperChat.User.profile.UserProfileDTO(u.username, "
            + "(SELECT COALESCE(COUNT(f1), 0) FROM Follower f1 WHERE f1.followee.id = u.id), "
            + "(SELECT COALESCE(COUNT(f2), 0) FROM Follower f2 WHERE f2.follower.id = u.id)) "
            + "FROM UserEntity u WHERE u.username = :username")
    Optional<UserProfileDTO> findUserProfileByUsername(String username);

    @Query("SELECT u.id FROM UserEntity u WHERE u.username = :username")
    Long findUserIdByUsername(@Param("username") String username);

    @Query("SELECT u.role.name FROM UserEntity u WHERE u.username = :username")
    String findUserRoleByUsername(@Param("username") String username);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
