package com.project.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.quiz.game.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class CategoryQuestionCountInfoDto {

    @JsonProperty("category_id")
    private int categoryId;

    @JsonProperty("category_question_count")
    private CategoryQuestionCountDto categoryQuestionCount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public static class CategoryQuestionCountDto {
        @JsonProperty("total_question_count")
        private int totalQuestionCount;
        @JsonProperty("total_easy_question_count")
        private int totalEasyQuestionCount;
        @JsonProperty("total_medium_question_count")
        private int totalMediumQuestionCount;
        @JsonProperty("total_hard_question_count")
        private int totalHardQuestionCount;

    }

    public CategoryQuestionCountInfoDto(int easyQuestionCount, int mediumQuestionCount, int hardQuestionCount) {
        this.categoryQuestionCount = new CategoryQuestionCountDto(easyQuestionCount+mediumQuestionCount+hardQuestionCount,
                easyQuestionCount, mediumQuestionCount, hardQuestionCount);
    }

    public int getTotalQuestionCount(){
        return categoryQuestionCount.totalQuestionCount;
    }

    public int getQuestionCountForDifficulty(Difficulty difficulty){
        return switch (difficulty) {
            case EASY -> categoryQuestionCount.totalEasyQuestionCount;
            case MEDIUM -> categoryQuestionCount.totalMediumQuestionCount;
            case HARD -> categoryQuestionCount.totalHardQuestionCount;
        };
    }
}
