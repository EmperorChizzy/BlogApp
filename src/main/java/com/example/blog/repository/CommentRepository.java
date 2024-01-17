package com.example.blog.repository;


import com.example.blog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    Comments getCommentById(Long id);


}
