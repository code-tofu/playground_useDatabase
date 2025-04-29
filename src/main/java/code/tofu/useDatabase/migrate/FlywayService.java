package code.tofu.useDatabase.migrate;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.CleanResult;
import org.flywaydb.core.api.output.MigrateResult;
import org.flywaydb.core.api.output.RepairResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/*
    Programmatic implementation of Flyway Service
 */
@Slf4j
@Service
public class FlywayService {

    @Autowired
    DataSource dataSource;

    @Value("${migrate.script.location}")
    String scriptLocations;

    @Value("${migrate.run-repair}")
    Boolean runRepair;

    Flyway flyway;

    @PostConstruct
    public void initFlyway(){
        flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(scriptLocations)
                .load();
    }

    public void migrateFlyway(){
        try {
            MigrateResult migrateResult = flyway.migrate();
            if (migrateResult.migrationsExecuted > 0) {
                log.info("Flyway Migration Completed");
            }
        } catch (Exception e){
            log.error("Error During Flyway Migration",e);
        }
    }

    public void migrateFlywayWithRepair(){
        try {
            if(runRepair){
                log.info("Running Flyway Repair");
                repairFlywayHistory();
            }
            log.info("Running Flyway Migration");
            MigrateResult migrateResult = flyway.migrate();
            if (migrateResult.migrationsExecuted > 0) {
                log.info("Flyway Migration Completed");
            }
        } catch (Exception e){
            log.error("Error During Flyway Migration",e);
        }
    }

    public void cleanupFlywayHistory(){
        CleanResult cleanResult = flyway.clean();
    }

    public void repairFlywayHistory(){
        try {
            RepairResult repairResult = flyway.repair();
        } catch (Exception e){
            log.error("Error During Flyway Repair",e);
        }
    }


}
