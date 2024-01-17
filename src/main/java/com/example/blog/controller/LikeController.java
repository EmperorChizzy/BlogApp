package com.example.blog.controller;
import com.example.blog.entity.Like;
import com.example.blog.entity.Users;
import com.example.blog.serviceImpl.LikeServiceImpl;
import com.example.blog.serviceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeServiceImpl likeService;

    @Autowired
    public LikeController(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<Like> likePost(
            @PathVariable Long postId,
            @PathVariable Long userId) {
        Like savedLike = likeService.likePost(postId, userId);
        return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
    }

    @PostMapping("/comment/{commentId}/user/{userId}")
    public ResponseEntity<Like> likeComment(
            @PathVariable Long commentId,
            @PathVariable Long userId) {
        Like savedLike = likeService.likeComment(commentId, userId);
        return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
    }

}