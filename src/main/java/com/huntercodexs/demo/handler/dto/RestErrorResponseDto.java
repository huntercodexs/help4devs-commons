package com.huntercodexs.demo.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "This object refers to error in the application", name = "RestErrorResponseDto")
public class RestErrorResponseDto {
    int codeError;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String tcn;
}
