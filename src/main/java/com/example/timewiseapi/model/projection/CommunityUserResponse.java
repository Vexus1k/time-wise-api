package com.example.timewiseapi.model.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}

