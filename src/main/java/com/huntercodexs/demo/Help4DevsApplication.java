package com.huntercodexs.demo;

import com.huntercodexs.demo.services.system.hardsys.core.Help4DevsHardSysCliOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Help4DevsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Help4DevsApplication.class, args);
        hardsysRunner(args);
    }

    private static void hardsysRunner(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("hardsys") && !args[1].isEmpty()) {
                Help4DevsHardSysCliOutput cliOutput = new Help4DevsHardSysCliOutput();
                cliOutput.printer(
                        args[1],
                        "HARDSYS CLI TABLE - https://huntercodexs.com",
                        80);
            }
        }
    }

}
