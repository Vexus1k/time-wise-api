package com.example.timewiseapi.model.projection;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityResponse {
    private int id;
    private String content;
    private LocalDateTime publicationDate;
    private CommunityUserResponse user;
    private List<CommentResponse> comments;
    private int likesCount;
    private boolean userLiked;
}
