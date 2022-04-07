package com.project.quiz.services;

import com.project.quiz.dto.CategoriesDto;
import com.project.quiz.dto.QuestionsDto;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Log
public class QuizDataService {

    public void getQuizCategories(){
        RestTemplate restTemplate = new RestTemplate();
        CategoriesDto result = restTemplate.getForObject("https://opentdb.com/api_category.php", CategoriesDto.class);
        assert result != null;
        log.info("Quiz categories: " + result.getCategories());
    }

    public void getQuizQuestions(){
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api.php")
                .queryParam("amount",2)
                .queryParam("category", 25)
                .queryParam("difficulty", "medium")
                .build().toUri();

        QuestionsDto result = restTemplate.getForObject(uri, QuestionsDto.class);

    }
}
