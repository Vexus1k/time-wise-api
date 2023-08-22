package com.example.timewiseapi.logic;

import com.example.timewiseapi.model.*;
import com.example.timewiseapi.model.projection.*;
import com.example.timewiseapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final LikeService likeService;

    public List<CommunityResponse> getAllCommunityPostsWithComments(User user) {
        List<Community> communityPosts = communityRepository.findAll();
        List<CommunityResponse> communityResponses = new ArrayList<>();

        for (Community communityPost : communityPosts) {
            List<Comment> comments = communityPost.getComments();

            List<CommentResponse> commentResponses = new ArrayList<>();
            for (Comment comment : comments) {
                CommentResponse commentResponse = mapToCommentResponse(comment);
                commentResponses.add(commentResponse);
            }

            CommunityResponse communityResponse = mapToCommunityResponse(communityPost, user);
            communityResponse.setComments(commentResponses);
            communityResponse.setLikesCount(communityPost.getLikes().size());
            communityResponse.setUserLiked(likeService.userLikedPost(user, communityPost));
            communityResponses.add(communityResponse);
        }

        return communityResponses;
    }

    public Community getCommunityPostById(Integer postId) {
        return communityRepository.findById(postId).orElse(null);
    }

    public Community createCommunityPost(User user, String content) {
        Community newPost = new Community();
        newPost.setUser(user);
        newPost.setContent(content);
        newPost.setPublicationDate(LocalDateTime.now());

        return communityRepository.save(newPost);
    }

    public CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .commentDate(comment.getCommentDate())
            .user(mapToCommentUserResponse(comment.getUser()))
            .build();
    }

    public CommunityResponse mapToCommunityResponse(Community community, User user) {
        List<Comment> comments = community.getComments();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponse commentResponse = mapToCommentResponse(comment);
            commentResponses.add(commentResponse);
        }

        boolean userLiked = community.getLikes().stream()
                .anyMatch(like -> like.getUser().equals(user));

        return CommunityResponse.builder()
            .id(community.getId())
            .content(community.getContent())
            .publicationDate(community.getPublicationDate())
            .user(mapToCommunityUserResponse(community.getUser()))
            .comments(commentResponses)
            .userLiked(userLiked)
            .build();
    }

    public CommentUserResponse mapToCommentUserResponse(User user) {
        return CommentUserResponse.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
    }

    public CommunityUserResponse mapToCommunityUserResponse(User user) {
        return CommunityUserResponse.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .build();
    }
}
