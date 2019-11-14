package Lionbridge.Translations;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"services", "Lionbridge.Translations"})
public class TranslationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslationsApplication.class, args);
	}

}
