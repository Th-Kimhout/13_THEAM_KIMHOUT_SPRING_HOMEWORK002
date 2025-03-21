package org.hrd._13_theam_kimhout_spring_homework002;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "Online Learning Platform", version = "1.0", description = "Education is the spark that ignites curiosity and fuels lifelong learning, transcending the mere accumulation of knowledge."))
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
