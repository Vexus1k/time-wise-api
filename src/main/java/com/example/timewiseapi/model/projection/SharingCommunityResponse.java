package com.example.timewiseapi.model.projection;

import com.example.timewiseapi.model.Comment;
import com.example.timewiseapi.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingCommunityResponse {
    private int id;
    private SharingUserResponse user;
    private String content;
    private boolean userLiked;
    private int likesCount;
    private LocalDateTime publicationDate;
    private List<CommentResponse> comments;
}
