package com.example.blog.serviceImpl;


import com.example.blog.entity.Comments;
import com.example.blog.entity.Post;
import com.example.blog.entity.Users;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    public Comments createComment(Long postId, Comments comment, Users currentUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
        Users user = userRepository.findById(currentUser.getId())
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + currentUser.getId()));
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comments getCommentById(Long commentId) {
        return commentRepository.getCommentById(commentId);
    }
}