package com.example.blog.repository;

import com.example.blog.entity.Like;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, Users user);
    List<Like> findByPostId(Long blogPostId);
}
