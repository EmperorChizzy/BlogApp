package com.example.blog.controller;
import com.example.blog.dto.EditPostRequestDto;
import com.example.blog.dto.PostRequestDto;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.serviceImpl.PostServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
//@SecurityRequirement(name = "Bearer Authentication")
@RestController
//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RequestMapping("/api/v1/post")
public class PostController {
    private PostServiceImpl postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }



    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto, @PathVariable  Long userId ){
        return postService.createPost(postRequestDto, userId);
    }

    @PutMapping("/edit/{postId}/{userId}")
    public ResponseEntity<String> editPost(@RequestBody EditPostRequestDto editPostRequestDto, @PathVariable Long postId, @PathVariable Long userId){
        return postService.editPostContent(editPostRequestDto, userId, postId);
    }

    @GetMapping("/all-post")
    public ResponseEntity<List<Post>> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/get-post/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId){
        return postService.findPostById(postId);
    }

    @PutMapping("/edit-post/{postId}")
    public ResponseEntity<Post> editPostById(@PathVariable("postId") Long postId, @RequestBody Post newPost){
        return postService.editPostById(postId, newPost);
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId, @AuthenticationPrincipal Users currentUser){
        postService.deletePostById(postId, currentUser);
        return ResponseEntity.ok().build();
    }


}