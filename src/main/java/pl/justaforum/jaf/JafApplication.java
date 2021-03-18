package pl.justaforum.jaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JafApplication {

    public static void main(String[] args) {
        SpringApplication.run(JafApplication.class, args);
    }

}
