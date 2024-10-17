package codexstester.bdd.stepsdef.integration;

import com.huntercodexs.demo.Help4DevsApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {Help4DevsApplication.class}
)
public class CucumberSpringIntegration {
}
