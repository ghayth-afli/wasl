package com.mzo.wasl.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewRequest {
    @NotBlank
    private String comment;
    @NotBlank
    private Short rating;
}
