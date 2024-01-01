package com.mzo.wasl.dto.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegularWithProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String country;
    private String city;
    private String phoneNumber;
    private String language ;
    private String image;
}
