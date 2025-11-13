package com.example.VKUserAndGroup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class RequestDto {

    @NotNull
    @Schema(description = "User ID", example = "78385")
    String user_id;

    @NotNull
    @Schema(description = "Group ID", example = "93559769")
    String group_id;
}
