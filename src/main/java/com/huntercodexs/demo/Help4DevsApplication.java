package com.huntercodexs.demo;

import com.huntercodexs.demo.services.system.hardsys.Help4DevsHardSys;
import com.huntercodexs.demo.services.system.hardsys.dto.Help4DevsHardSysResourcesDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands.*;

@SpringBootApplication
public class Help4DevsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Help4DevsApplication.class, args);

        if (args.length > 0) {

            System.out.println("Load information: Please wait...");
            Help4DevsHardSys hardSys;

            switch (args[0]) {
                case "inxi":
                    hardSys = new Help4DevsHardSys(INXI);
                    break;
                case "hwinfo":
                    hardSys = new Help4DevsHardSys(HWINFO);
                    break;
                case "lshw":
                    hardSys = new Help4DevsHardSys(LSHW);
                    break;
                default:
                    hardSys = new Help4DevsHardSys(AUTO);
                    break;
            }

            Help4DevsHardSysResourcesDto result = hardSys.resources().builder();
            System.out.println(result);
        }

    }

}
