package net.engineeringdigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(JournalApplication.class, args);
		ConfigurableEnvironment configurableEnvironment = configurableApplicationContext.getEnvironment();
		System.out.println(configurableEnvironment.getActiveProfiles()[0]);
	}

	@Bean
	public PlatformTransactionManager anything(MongoDatabaseFactory mb){
		return new MongoTransactionManager(mb);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
