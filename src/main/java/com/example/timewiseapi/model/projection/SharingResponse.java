package com.example.timewiseapi.model.projection;

import com.example.timewiseapi.model.Community;
import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingResponse {
    private int id;
    private SharingUserResponse user;
    private SharingCommunityResponse sharedPost;
    private LocalDateTime sharingDate;
}
