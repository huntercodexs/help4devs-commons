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
public class TemplateControllerDto {
    @NotNull @NotBlank @NotEmpty
    String name;
    String address;
    String phone;
    String email;
}
