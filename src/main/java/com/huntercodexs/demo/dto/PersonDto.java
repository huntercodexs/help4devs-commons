package com.huntercodexs.demo.dto;

import com.huntercodexs.demo.annotation.valitation.Help4DevsValidationAnnotation;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    int id;
    int age;
    @Help4DevsValidationAnnotation()
    String name;
}
