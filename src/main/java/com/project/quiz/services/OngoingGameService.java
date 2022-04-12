package com.project.quiz.services;

import com.project.quiz.dto.CategoriesDto;
import com.project.quiz.dto.QuestionsDto;
import com.project.quiz.game.Difficulty;
import com.project.quiz.game.GameOptions;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
@Log
public class OngoingGameService {
    private int currentQuestionId;
    private GameOptions gameOptions;
    private int points;

    private List<QuestionsDto.QuestionDto> questions;

    @Autowired
    private QuizDataService quizDataService;

    public void init(GameOptions gameOptions){
        this.gameOptions = gameOptions;
        this.currentQuestionId = 0;
        this.points = 0;

        this.questions = quizDataService.getQuizQuestions(gameOptions);
    }

    public int getCurrentQuestionNumber(){
        return this.currentQuestionId+1;
    }

    public int getTotalQuestionNumber(){
        return this.questions.size();
    }

    public String getCurrentQuestion(){
        QuestionsDto.QuestionDto dto = questions.get(currentQuestionId);
        return dto.getQuestion();
    }

    public List<String> getCurrentQuestionAnswersInRandomOrder(){
        QuestionsDto.QuestionDto dto = questions.get(currentQuestionId);

        List<String> answers = new ArrayList<>();
        answers.add(dto.getCorrectAnswer());
        answers.addAll(dto.getIncorrectAnswers());

        Collections.shuffle(answers);
        return answers;
    }

    public boolean checkAnswerForCurrentQuestionAndUpdatePoints(String userAnswer){
        QuestionsDto.QuestionDto dto = questions.get(currentQuestionId);
        boolean correct = dto.getCorrectAnswer().equals(userAnswer);
        if(correct){
            points++;
        }
        return correct;
    }

    public boolean proceedToNextQuestion(){
        currentQuestionId++;
        return currentQuestionId < questions.size();
    }

    public Difficulty getCurrentDifficulty(){
        return gameOptions.getDifficulty();
    }

    public String getCategoryName(){
        Optional<String> category = quizDataService.getQuizCategories().stream()
                .filter(categoryDto -> categoryDto.getId() == gameOptions.getCategoryId())
                .map(CategoriesDto.CategoryDto::getName).findAny();
        return category.orElse(null);
    }

    public int getPoints(){
        return this.points;
    }
}
