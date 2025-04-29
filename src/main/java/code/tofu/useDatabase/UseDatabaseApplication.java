package code.tofu.useDatabase;

import code.tofu.useDatabase.migrate.FlywayMigrateApplication;
import code.tofu.useDatabase.migrate.FlywayService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class UseDatabaseApplication {

	@PostConstruct
	void start(){
		log.info("Application started!");
	}

	public static void main(String[] args) {
		log.info(Arrays.toString(args));
		// for use with a pre-deploy script
		String migrateFlag = System.getProperty("flywayMigrate");
		if(migrateFlag != null && migrateFlag.equalsIgnoreCase("true")){
			log.info("Flyway Migrate Flag Active, Running Flyway migrate");
			ApplicationContext applicationContext = SpringApplication.run(FlywayMigrateApplication.class, args);
			FlywayService flywayService = applicationContext.getBean(FlywayService.class);
			flywayService.migrateFlywayWithRepair();
			System.exit(0);
		} else {
			SpringApplication.run(UseDatabaseApplication.class, args);
		}

	}

}
