package blue.builder.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlueBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueBuilderApplication.class, args);
	}

}
