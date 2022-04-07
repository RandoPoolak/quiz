package com.project.quiz;

import com.project.quiz.database.entities.PlayerEntity;
import com.project.quiz.database.repositories.PlayerRepository;
import com.project.quiz.services.QuizDataService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Log
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private QuizDataService quizDataService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        log.info("Executing startup actions...");

        playerRepository.save(new PlayerEntity("Gregory"));

    }
}
