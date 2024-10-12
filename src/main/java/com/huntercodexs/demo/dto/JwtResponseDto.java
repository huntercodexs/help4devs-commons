package com.huntercodexs.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
    @NotNull @NotBlank @NotEmpty
    String jwt;
}
