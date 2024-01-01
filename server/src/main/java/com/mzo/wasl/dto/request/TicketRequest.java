package com.mzo.wasl.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TicketRequest {
    @NotBlank
    private String objet;
    @NotBlank
    private String description;
}
