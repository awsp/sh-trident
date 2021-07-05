package com.areamode.shtrident;

import com.areamode.shtrident.data.model.Program;
import com.areamode.shtrident.data.repo.ProgramRepository;
import com.areamode.shtrident.service.AnisonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

@SpringBootApplication
public class SHTridentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SHTridentApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> onReady(final AnisonService anisonService, final ProgramRepository programRepository) {
        return event -> {
            for (Program program : programRepository.findAll()) {
                System.out.println(program.getTitle());
            }
//            try {
//                anisonService.saveProgram(Paths.get("./importing/program.csv"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        };
    }

}
