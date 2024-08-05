package com.huntercodexs.demo.services.annotation;

import com.huntercodexs.demo.annotation.logger.Help4DevsLoggerAnnotation;
import com.huntercodexs.demo.annotation.valitation.Help4DevsValidationAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsAnnotationService {

    @Help4DevsLoggerAnnotation()
    public void useHelp4DevsLoggerAnnotation() {
        System.out.println("Running useHelp4DevsLoggerAnnotation");
    }

    public void useHelp4DevsValidationAnnotation(Person person) {
        System.out.println("Running useHelp4DevsValidationAnnotation");
        System.out.println(person.getId());
        System.out.println(person.getAge());
        System.out.println(person.getName());
    }

    @Getter
    @Setter
    public static class Person {
        int id;
        int age;
        @Help4DevsValidationAnnotation()
        String name;
    }

}
