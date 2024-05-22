package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class Help4DevsGenericUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void substringTest() {
        String register = "000222229999999999220324";
        System.out.println(register.substring(0, 8).trim());
    }

    @Test
    public void loopTest() {

        int maxSizeReport = 500;
        int splitter = (int) ceil((double) 5500 / 500);

        for (int i = 0; i < splitter; i++) {

            System.out.println("INT I: " + i);

            for (int j = i*maxSizeReport; j < (i*maxSizeReport)+maxSizeReport; j++) {
                System.out.println("INT J: " + j);
            }
        }
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SampleDto {
        int id;
        String name;
    }

    private List<? extends SampleDto> listDto() {

        List<SampleDto> sampleDtoList = new ArrayList<>();

        for (int k = 0; k < 1000; k++) {
            SampleDto sampleDto = new SampleDto();
            sampleDto.setId(k);
            sampleDto.setName("Testing... " + k);
            sampleDtoList.add(sampleDto);
        }

        return sampleDtoList;
    }

    @Test
    public void loopListDtoTest() {
        List<? extends SampleDto> sampleDto = listDto();
        System.out.println(sampleDto);

        int maxSizeReport = 100;

        int splitter = (int) ceil((double) sampleDto.size() / maxSizeReport);

        for (int i = 0; i < splitter; i++) {
            List<? extends SampleDto> sampleDtoCurrent = sampleDto.subList(i*maxSizeReport,(i*maxSizeReport)+maxSizeReport);

            System.out.println("I: " + i);
            System.out.println(("["+(i*maxSizeReport)+"]:["+((i*maxSizeReport)+maxSizeReport))+"]");
            System.out.println(sampleDtoCurrent);
        }
    }

}
