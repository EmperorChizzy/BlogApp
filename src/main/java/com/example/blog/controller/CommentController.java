package com.example.blog.controller;
import com.example.blog.entity.Comments;
import com.example.blog.entity.Users;
import com.example.blog.serviceImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/add/{postId}")
    public ResponseEntity<Comments> createComment(@PathVariable Long postId,  @RequestBody Comments comment,  @AuthenticationPrincipal Users currentUser) {
        Comments createdComment = commentService.createComment(postId, comment, currentUser);
        return ResponseEntity.ok(createdComment);
    }
}