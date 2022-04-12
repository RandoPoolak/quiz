package com.project.quiz.game;

import java.util.Collection;
import java.util.EnumSet;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    public Difficulty getClosestDifficulty() {
        return switch (this) {
            case EASY, HARD -> MEDIUM;
            case MEDIUM -> HARD;
        };
    }

    public static Difficulty calculateNextDifficulty(Collection<Difficulty> difficulties) {
        if (difficulties == null || difficulties.isEmpty()) {
            return null;
        }
        if (difficulties.size() == 1) {
            return difficulties.iterator().next().getClosestDifficulty();
        }
        EnumSet<Difficulty> missingDifficulties = EnumSet.complementOf(EnumSet.copyOf(difficulties));
        if (missingDifficulties.isEmpty()) {
            return null;
        } else {
            return missingDifficulties.iterator().next();
        }
    }
}

