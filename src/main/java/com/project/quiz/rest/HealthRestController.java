package com.project.quiz.rest;

import com.project.quiz.dto.HealthCheckDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthRestController {

    @GetMapping
    public HealthCheckDto healthCheck(){
        HealthCheckDto dto = new HealthCheckDto(true, "It's working!");
        return dto;
    }
}
