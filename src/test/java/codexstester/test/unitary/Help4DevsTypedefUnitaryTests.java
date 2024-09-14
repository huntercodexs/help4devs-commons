package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.typedef.Help4DevsElement;
import com.huntercodexs.demo.services.typedef.Help4DevsElementKeyValue;
import com.huntercodexs.demo.services.typedef.Help4DevsElementList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Help4DevsTypedefUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void help4DevsElementIntegerTest() {

        Help4DevsElement<Integer> help4DevsElement = new Help4DevsElement<Integer>();

        help4DevsElement.setValue(1);

        codexsTesterAssertInt(1, help4DevsElement.getValue());

    }

    @Test
    public void help4DevsElementStringTest() {

        Help4DevsElement<String> help4DevsElement = new Help4DevsElement<String>();

        help4DevsElement.setValue("1");

        codexsTesterAssertText("1", help4DevsElement.getValue());

    }

    @Test
    public void help4DevsElementBooleanTest() {

        Help4DevsElement<Boolean> help4DevsElement = new Help4DevsElement<Boolean>();

        help4DevsElement.setValue(true);

        codexsTesterAssertBool(true, help4DevsElement.getValue());

    }

    @Test
    public void help4DevsElementKeyValueTest() {

        Help4DevsElementKeyValue<Integer, String> help4DevsElementKeyValue = new Help4DevsElementKeyValue<>();

        help4DevsElementKeyValue.setKey(1);
        help4DevsElementKeyValue.setValue("Item 1");

        codexsTesterAssertInt(1, help4DevsElementKeyValue.getKey());
        codexsTesterAssertText("Item 1", help4DevsElementKeyValue.getValue());

    }

    @Test
    public void help4DevsElementListTest() {

        Help4DevsElementList<List<String>> help4DevsElementList = new Help4DevsElementList<>();

        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDD", "EEE");

        help4DevsElementList.setList(list);

        for (String item : help4DevsElementList.getList()) {
            System.out.print(item+" ");
        }

    }

}
