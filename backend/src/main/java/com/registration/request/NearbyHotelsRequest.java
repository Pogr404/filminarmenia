package com.registration.request;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearbyHotelsRequest {

    @Hidden
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Rate cannot be null")
    @Positive(message = "Rate must be positive")
    private Float rate;

    @NotBlank(message = "Website cannot be empty")
    private String website;
}