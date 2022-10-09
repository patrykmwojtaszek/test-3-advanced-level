package pl.kurs.test3advancedlevel;

import com.fasterxml.uuid.Generators;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.Letter;
import pl.kurs.test3advancedlevel.service.IJobCreatorService;

import java.util.UUID;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Test3AdvancedLevelApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Test3AdvancedLevelApplication.class, args);
    }

}
