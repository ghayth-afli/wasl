package com.mzo.wasl.payload.request;

import java.util.Date;
import com.mzo.wasl.models.EStatus;
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