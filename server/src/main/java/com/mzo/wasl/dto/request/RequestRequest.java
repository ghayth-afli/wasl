package com.mzo.wasl.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRequest {
    @NotBlank
    private String description;
    @NotBlank
    private Double weight;
}