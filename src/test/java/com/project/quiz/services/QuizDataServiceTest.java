package com.project.quiz.services;

import com.project.quiz.dto.CategoryQuestionCountInfoDto;
import com.project.quiz.game.Difficulty;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.project.quiz.game.Difficulty.*;
import static org.junit.jupiter.api.Assertions.*;

class QuizDataServiceTest {

    @Test
    void calculateEachDifficultyQuestionCount_basicEasy() {
        CategoryQuestionCountInfoDto categoryQuestionCount = new CategoryQuestionCountInfoDto(5, 17, 13);
        Map<Difficulty, Integer> result = QuizDataService.calculateEachDifficultyQuestionCount(20, EASY, categoryQuestionCount);

        assertEquals(5, result.get(EASY));
        assertEquals(15, result.get(MEDIUM));
        assertNull(result.get(HARD));
    }
}