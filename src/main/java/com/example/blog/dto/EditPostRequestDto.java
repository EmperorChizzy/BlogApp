package com.example.blog.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
@AllArgsConstructor
public class EditPostRequestDto {
    private  String content;
    private String title;
}