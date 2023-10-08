package de.kleemann.co2_hsharz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = "de.kleemann.co2_hsharz")
public class Application {

    private static String PREFIX = "[CO2_HS-Harz] ";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(PREFIX + "API successfully started.");
    }

    @GetMapping("")
    public String greeting() {
        return PREFIX + "API successsfully started.";
    }

}
