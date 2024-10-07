package com.huntercodexs.demo.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuickJsonDto {
    public Object type;
    public Integer age;
    public String name;
    public String lastname;
    public String fullname;
    public String reference;
    public List<Object> address;
    public List<Object> contacts;
    public List<Object> numbers;
    public List<Object> family;
    public HashMap<Object,Object> map;
}
