package com.putfocus.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    @NotEmpty(message = "Title is required")
    private String title;
    private String description;
    @NotNull(message = "Session estimate is required")
    private int sessionEstimate;
    private boolean completed;
}
