package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.huntercodexs.demo.services.Help4DevsParserService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsParserUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void isNetJsonTest() {
        JSONObject jsonObject = new JSONObject();
        codexsTesterAssertBool(true, isNetJson(jsonObject));
    }

    @Test
    public void isOrgJsonTest() {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        codexsTesterAssertBool(true, isOrgJson(jsonObject));
    }

    @Test
    public void jsonRefactorTest() throws Exception {
        org.json.JSONObject json1 = new org.json.JSONObject();
        json1.put("name", "John");
        json1.put("age", "34");
        json1.put("mail", "john@testmail.com");
        json1.put("site", "http://www.johnsmith.com");
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Run");
        interest.add("Cycle");
        interest.add("Films");
        json1.put("interest", interest);
        List<String> strings = new ArrayList<>();
        strings.add(String.valueOf(1));
        strings.add("2");
        strings.add("3");
        strings.add("4");
        json1.put("strings", strings);
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1893);
        numbers.add(293);
        numbers.add(39);
        numbers.add(3);
        json1.put("numbers", numbers);
        List<String> strings2 = new ArrayList<>();
        strings2.add("1, 2,3, 4, 5 ,6, 123 345 987 345");
        json1.put("strings2", strings2);
        List<String> stringNumbers = new ArrayList<>();
        stringNumbers.add("n1");
        stringNumbers.add("x2");
        stringNumbers.add("e3");
        stringNumbers.add("m4");
        json1.put("stringNumbers", stringNumbers);

//        Object object1 = json1;
//        System.out.println("JSON 1");
//        System.out.println(json1);
//        String string1 = json1.toString();
//        System.out.println("OBJECT 1");
//        System.out.println(object1);
//        System.out.println("STRING 1");
//        System.out.println(string1);

        String result = jsonRefactor("complex", json1);
        stdout(result);
        codexsTesterAssertText(
                "{\"site\":\"http://www.johnsmith.com\",\"mail\":\"john@testmail.com\",\"strings\":[1,2,3,4],\"stringNumbers\":[\"n1\",\"x2\",\"e3\",\"m4\"],\"interest\":[\"Soccer\",\"Run\",\"Cycle\",\"Films\"],\"name\":\"John\",\"numbers\":[1893,293,39,3],\"strings2\":[1,2,3,4,\"5 \",6,\"123 345 987 345\"],\"age\":34}",
                result);
    }

    @Test
    public void objectToNetJsonTest() throws Exception {
        Object object = new Object();
        object = "name=James,id=111,test=1";
        System.out.println(objectToNetJson(object));
    }

    @Test
    public void jsonFromLinkedHashMapTest() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();

        List<String> addressList = new ArrayList<>();
        List<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> contact = new HashMap<>();

        addressList.add("Street One");
        addressList.add("100");
        linkedHashMap.put("name", "James");
        linkedHashMap.put("address", addressList);

        contact.put("phone", "12345678890");
        contact.put("email", "email@email.com");
        arrayList.add(contact);
        linkedHashMap.put("contacts", arrayList);

        Object[] fields = new Object[]{"name", "contacts"};

        JSONObject jsonResult = jsonFromLinkedHashMap(linkedHashMap, fields);

        /*Specific fields*/
        codexsTesterAssertText(
                "{\"name\":\"James\",\"contacts\":[{\"phone\":\"12345678890\",\"email\":\"email@email.com\"}]}",
                jsonResult.toJSONString());

        jsonResult = jsonFromLinkedHashMap(linkedHashMap, null);

        /*All fields*/
        codexsTesterAssertText(
                "{\"address\":[\"Street One\",\"100\"],\"name\":\"James\",\"contacts\":[{\"phone\":\"12345678890\",\"email\":\"email@email.com\"}]}",
                jsonResult.toJSONString());

    }

}
